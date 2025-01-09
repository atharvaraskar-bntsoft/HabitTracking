document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('habitProgressForm');
    const habitDropdown = document.getElementById('habitSelect');
    const targetUnitSpan = document.getElementById('targetUnit');

    // Function to populate habit dropdown
    async function populateHabitDropdown() {
        try {
            const response = await fetch('http://localhost:8080/habittrackerapi/specialhabit');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const habits = await response.json();

            habits.forEach(habit => {
                const option = document.createElement('option');
                option.value = habit.id;
                option.textContent = habit.name;
                habitDropdown.appendChild(option);
            });

            habitDropdown.addEventListener('change', updateTargetUnit);

        } catch (error) {
            console.error('Error fetching habits:', error);
        }
    }

    // Function to update target unit
    async function updateTargetUnit() {
        const selectedHabitId = habitDropdown.value;
        if (!selectedHabitId) {
            targetUnitSpan.textContent = ''; 
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/habittrackerapi/specialhabit/${selectedHabitId}`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const habit = await response.json();
            targetUnitSpan.textContent = habit.targetUnit ? `(${habit.targetUnit})` : '';

        } catch (error) {
            console.error('Error fetching habit details:', error);
        }
    }

    // Populate habits when page loads
    populateHabitDropdown();

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const date = form.querySelector('input[name="date"]').value;
        const progress = form.querySelector('input[name="progress"]').value;
        const isCompleted = form.querySelector('input[name="isCompleted"]:checked').value === 'true';
        const note = form.querySelector('textarea[name="note"]').value;
        const specialHabitId = form.querySelector('select[name="specialHabitId"]').value;
        const userId = parseInt(localStorage.getItem("USERID")); // Ensure it's a number

        // Basic validation to avoid NaN or empty fields
        if (!date || !progress || isNaN(progress) || !specialHabitId || isNaN(userId)) {
            alert("Please fill in all required fields correctly.");
            return;
        }

        const data = {
            specialHabitId: parseInt(specialHabitId),
            userId: userId,
            date: date,
            progress: parseInt(progress),
            isCompleted: isCompleted,
            note: note
        };

        try {
            const response = await fetch('http://localhost:8080/habittrackerapi/specialhabitprogress', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const result = await response.json();
            console.log('Success:', result);
            alert('Habit progress recorded successfully!');

            form.reset();
            targetUnitSpan.textContent = '';

        } catch (error) {
            console.error('Error:', error);
            alert('Failed to record habit progress.');
        }
    });

    document.getElementById('complete').checked = true;

    document.getElementById('complete').addEventListener('change', function () {
        if (this.checked) {
            console.log("Habit marked as complete.");
        }
    });

    document.getElementById('incomplete').addEventListener('change', function () {
        if (this.checked) {
            console.log("Habit marked as incomplete.");
        }
    });
});
