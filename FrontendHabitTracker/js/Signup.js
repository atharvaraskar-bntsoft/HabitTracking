document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', async function(event) {
        event.preventDefault(); // Prevent the default form submission

        const username = form.querySelector('input[name="username"]').value;
        const password = form.querySelector('input[name="psw"]').value;

        // Check for spaces in the username
        if (/\s/.test(username)) {
            alert('Username cannot contain spaces.');
            return;
        }

        // Construct the request payload
        const data = {
            username: username,
            password: password
        };

        try {
            // Make the POST request
            const response = await fetch('http://localhost:8080/habittrackerapi/users', {
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
            alert('Sign-up successful');
            window.location.href = 'index.html';

        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred during sign-up');
        }
    });
});
