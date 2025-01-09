document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('habitProgressForm');
    const completeBtn = document.getElementById('completeBtn');
    const incompleteBtn = document.getElementById('incompleteBtn');
    let isCompleted = false; // Track completion status

    // Event listener for complete/incomplete buttons
    completeBtn.addEventListener('click', () => {
        isCompleted = true;
        alert('Habit marked as complete for the day!');
    });

    incompleteBtn.addEventListener('click', () => {
        isCompleted = false;
        alert('Habit marked as incomplete for the day!');
    });

    // Handle form submission
    form.addEventListener('submit', async function(event) {
        event.preventDefault();  // Prevent default form submission

        const specialHabitId = document.getElementById('specialHabitId').value;
        const progressValue = parseInt(document.getElementById('progress').value, 10);
        const date = new Date().toISOString().split('T')[0]; // Get current date in YYYY-MM-DD format

        const userId = localStorage.getItem('USERID'); // Retrieve user ID (stored previously)

        // Payload for progress update
        const data = {
            specialHabitId: specialHabitId,
            date: date,
            progress: progressValue,
            isCompleted: isCompleted
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
            alert('Progress updated successfully!');
            console.log('Success:', result);
            form.reset(); // Reset form fields

        } catch (error) {
            console.error('Error:', error);
            alert('Error updating progress');
        }
    });
});

