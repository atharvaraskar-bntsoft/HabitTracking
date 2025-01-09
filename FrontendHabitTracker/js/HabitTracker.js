document.addEventListener('DOMContentLoaded', function () {
    const habitForm = document.getElementById('add-habit-form');
    const habitList = document.getElementById('habit-list');
    const calendarTable = document.getElementById('habit-calendar');


    function fetchHabits() {
        fetch('/api/habits')
            .then(response => response.json())
            .then(data => {
                habitList.innerHTML = '';
                data.forEach(habit => {
                    const li = document.createElement('li');
                    li.textContent = `${habit.name}: ${habit.description}`;
                    habitList.appendChild(li);
                });
            });
    }

    // Handle habit form submission
    habitForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const habit = {
            name: document.getElementById('habit-name').value,
            description: document.getElementById('habit-description').value,
            goal: document.getElementById('habit-goal').value,
        };

        fetch('/api/habits', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(habit),
        })
        .then(response => response.json())
        .then(data => {
            fetchHabits(); // Refresh habit list
        });

        habitForm.reset();
    });

    // Fetch habits on page load
    fetchHabits();
});
