document.addEventListener('DOMContentLoaded', function() {
    const habitList = document.getElementById('habitList');
    const refreshButton = document.getElementById('refreshButton');

    // Function to fetch and display habits
    async function fetchAndDisplayHabits() {
        const userId = localStorage.getItem('USERID');
        if (!userId) {
            alert('User is not logged in.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/habittrackerapi/habit');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const habits = await response.json();
            habitList.innerHTML = ''; // Clear any existing habits

            habits.forEach(habit => {
                const habitItem = document.createElement('li');
                habitItem.className = 'habit-item';
                habitItem.innerHTML = `
                    <h2>${habit.name}</h2>
                    <p><span>ID:</span> ${habit.id}</p>
                    <p><span>Description:</span> ${habit.description}</p>
                    <p><span>Goal:</span> ${habit.goal}</p>
                    <p><span>Frequency:</span> ${habit.frequency}</p>
                    <p><span>Start Date:</span> ${new Date(habit.startDate).toLocaleDateString()}</p>
                    <p><span>End Date:</span> ${habit.endDate ? new Date(habit.endDate).toLocaleDateString() : 'N/A'}</p>
                `;
                habitList.appendChild(habitItem);
            });
        } catch (error) {
            console.error('Error fetching habits:', error);
            alert('An error occurred while fetching habits.');
        }
    }

    // Initial fetch and display
    fetchAndDisplayHabits();

    // Refresh button functionality
    refreshButton.addEventListener('click', fetchAndDisplayHabits);
});
