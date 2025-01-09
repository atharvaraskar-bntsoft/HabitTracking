document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.habit-form'); // Ensure the form class matches
    const quotes = [
        "The secret of your future is hidden in your daily routine.",
        "Believe you can and you're halfway there.",
        "The journey of a thousand miles begins with one step." ,
        "Success is the sum of small efforts, repeated day in and day out.",
       
    ];
    let quoteIndex = 0;

    // Function to rotate quotes
    function rotateQuotes() {
        const quoteElement = document.querySelector('.header p');
        if (quoteElement) {
            quoteElement.textContent = quotes[quoteIndex];
            quoteIndex = (quoteIndex + 1) % quotes.length;
        }
    }

    // Start rotating quotes
    setInterval(rotateQuotes, 5000); // Rotate every 5 seconds

    form.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form data
        const date = form.querySelector('input[name="date"]').value;
        const status = form.querySelector('input[name="status"]:checked').value === 'true';
        const note = form.querySelector('textarea[name="note"]').value;
        const habitId = form.querySelector('input[name="habitId"]').value;
        const userId = localStorage.getItem("USERID"); // Ensure this value is stored in localStorage

        // Construct the request payload
        const data = {
            date: date,
            status: status,
            note: note,
            habitId: parseInt(habitId), // Ensure habitId is a number
            userId: parseInt(userId)    // Ensure userId is a number
        };

        try {
            // Make the POST request
            const response = await fetch('http://localhost:8080/habittrackerapi/habittracking', {
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
            alert('Habit tracking data added successfully!');
            
            // Optionally reset the form or redirect
            form.reset(); // Reset the form fields
            // window.location.href = 'some-other-page.html'; // Redirect if needed

        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while adding the habit tracking data.');
        }
    });
});
