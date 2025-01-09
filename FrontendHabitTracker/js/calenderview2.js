document.addEventListener('DOMContentLoaded', () => {
    const habitMenu = document.getElementById('habit-menu');
    const habits = [
        'Exercise', 'Gym', 'Meditation', 'Reading'
    ];

    function generateHabitMenu() {
        let menuHTML = '';
        habits.forEach(habit => {
            menuHTML += `<li data-habit="${habit}" onclick="redirectToDetail('${habit}')">${habit}</li>`;
        });
        habitMenu.innerHTML = menuHTML;
    }

    window.redirectToDetail = function(habit) {
        window.location.href = `calderdetail.html?habit=${encodeURIComponent(habit)}`;
    };

    generateHabitMenu();
});
