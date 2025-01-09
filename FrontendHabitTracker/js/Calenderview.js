document.addEventListener('DOMContentLoaded', () => {
    const habitMenu = document.getElementById('habit-menu');
    const calendarElement = document.getElementById('calendar');
    const monthYearElement = document.getElementById('month-year');
    const prevMonthButton = document.getElementById('prev-month');
    const nextMonthButton = document.getElementById('next-month');
    const notesElement = document.getElementById('notes');

    const habits = [
        'Exercise', 'Gym', 'Meditation', 'Reading'
    ];

    let currentDate = new Date();
    let selectedHabit = '';

    function generateHabitMenu() {
        let menuHTML = '';
        habits.forEach(habit => {
            menuHTML += `<li data-habit="${habit}">${habit}</li>`;
        });
        habitMenu.innerHTML = menuHTML;
    }

    function generateCalendar(date, habit) {
        const year = date.getFullYear();
        const month = date.getMonth();
        const firstDay = new Date(year, month, 1);
        const lastDay = new Date(year, month + 1, 0);

        const firstDayOfWeek = firstDay.getDay();
        const lastDateOfMonth = lastDay.getDate();

        let calendarHTML = '<thead><tr><th>Day</th><th>Check</th><th>Notes</th></tr></thead><tbody>';

        // Add empty cells for days before the start of the month
        calendarHTML += '<tr>';
        for (let i = 0; i < firstDayOfWeek; i++) {
            calendarHTML += '<td></td><td></td><td></td>';
        }

        // Add days of the month
        for (let day = 1; day <= lastDateOfMonth; day++) {
            if ((firstDayOfWeek + day - 1) % 7 === 0 && day > 1) {
                calendarHTML += '</tr><tr>';
            }
            calendarHTML += `<td>${day}</td><td><input type="checkbox" class="checkbox" data-day="${day}" data-habit="${habit}"></td><td><textarea class="notes" data-day="${day}" data-habit="${habit}"></textarea></td>`;
        }

        // Add empty cells for days after the end of the month
        const remainingDays = (firstDayOfWeek + lastDateOfMonth) % 7;
        if (remainingDays > 0) {
            for (let i = remainingDays; i < 7; i++) {
                calendarHTML += '<td></td><td></td><td></td>';
            }
        }

        calendarHTML += '</tr></tbody>';
        calendarElement.innerHTML = calendarHTML;

        // Update month-year display
        monthYearElement.textContent = `${date.toLocaleString('default', { month: 'long' })} ${year}`;
    }

    function updateCalendar(offset) {
        currentDate.setMonth(currentDate.getMonth() + offset);
        generateCalendar(currentDate, selectedHabit);
    }

    function handleHabitClick(event) {
        const habit = event.target.dataset.habit;
        if (habit) {
            selectedHabit = habit;
            generateCalendar(currentDate, selectedHabit);
        }
    }

    // Event listeners for habit menu and month navigation
    habitMenu.addEventListener('click', handleHabitClick);
    prevMonthButton.addEventListener('click', () => updateCalendar(-1));
    nextMonthButton.addEventListener('click', () => updateCalendar(1));

    // Initialize
    generateHabitMenu();
});
