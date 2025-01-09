document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('#addHabitForm');
    const isTimeRelatedCheckbox = form.querySelector('#isTimeRelated');
    const timeField = form.querySelector('#timeField');

    // Toggle time field visibility based on the checkbox
    isTimeRelatedCheckbox.addEventListener('change', function() {
        if (isTimeRelatedCheckbox.checked) {
            timeField.style.display = 'block';
        } else {
            timeField.style.display = 'none';
        }
    });

    form.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form data
        const habitName = form.querySelector('input[name="habitName"]').value;
        const description = form.querySelector('textarea[name="description"]').value;
        const goal = parseInt(form.querySelector('input[name="goal"]').value, 10);
        const target = parseInt(form.querySelector('input[name="target"]').value, 10);
        const goalUnit = form.querySelector('select[name="goalUnit"]').value;
        const targetUnit = form.querySelector('input[name="targetUnit"]').value;
        const frequency = form.querySelector('select[name="frequency"]').value.toUpperCase();
        const startDate = form.querySelector('input[name="startDate"]').value;
        const endDate = form.querySelector('input[name="endDate"]').value;
        const userId = 1;  // Example user ID; you can fetch from localStorage
        const isTimeRelated = isTimeRelatedCheckbox.checked;
        const targetTime = isTimeRelated ? form.querySelector('input[name="targetTime"]').value : null;

        // Validate date range
        if (new Date(startDate) > new Date(endDate)) {
            alert('End date must be after start date.');
            return;
        }

        // Construct the request payload
        const data = {
            name: habitName,
            description: description,
            goal: goal,
            target: target,
            goalUnit: goalUnit,
            targetUnit: targetUnit,
            frequency: frequency,
            startDate: startDate,
            endDate: endDate,
            userId: userId,
            targetTime: targetTime // Add targetTime if it's time-related, otherwise null
        };

        try {
            // Make the POST request
            const response = await fetch('http://localhost:8080/habittrackerapi/specialhabit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                const responseBody = await response.text();
                let errorMessage = 'Network response was not ok';
                try {
                    const errorData = JSON.parse(responseBody);
                    errorMessage = errorData.message || errorMessage;
                } catch (jsonError) {
                    errorMessage = responseBody;
                }

                throw new Error(errorMessage);
            }

            const result = await response.json();
            alert('Habit added successfully!');
            form.reset(); // Reset the form after successful submission
        } catch (error) {
            console.error('Error submitting form:', error);
            alert('Error adding habit: ' + error.message);
        }
    });
});
