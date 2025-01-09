document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('#addHabitForm'); // Ensure the form ID matches

    form.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form data
        const name = form.querySelector('input[name="habitName"]').value;
        const description = form.querySelector('textarea[name="description"]').value;
        const goal = form.querySelector('input[name="goal"]').value;
        const frequency = form.querySelector('select[name="frequency"]').value;
        const startDate = form.querySelector('input[name="startDate"]').value;
        const endDate = form.querySelector('input[name="endDate"]').value;
        const userId =localStorage.getItem("USERID");
        
        // Construct the request payload
        const data = {
            name: name,
            description: description,
            goal: goal,
            frequency: frequency,
            startDate: startDate,
            endDate: endDate,
            userId:userId
        };

        try {
            // Make the POST request
            const response = await fetch('http://localhost:8080/habittrackerapi/habit', {
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
            alert('Habit added successfully!');
            
            // Optionally reset the form or redirect
            form.reset(); // Reset the form fields
            // window.location.href = 'some-other-page.html'; // Redirect if needed

        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while adding the habit.');
        }
    });
});
