document.querySelector('form').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="psw"]').value;

    try {
        // Sending the login request to the backend
        const response = await fetch('http://localhost:8080/habittrackerapi/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        if (response.ok) {
            const userData = await response.json(); // Handle successful authentication response
            console.log(userData);
            localStorage.setItem("USERID", userData.id);

            alert(`Welcome, ${userData.username}!`); // Show a welcome message

            // Sending the email
            Email.send({
                SecureToken: "n_d0Qa7_Wa1B_ybt1", // Replace with your secure token
                To: "atharvaraskar20@gmail.com",
                From: "atharvaraskar20@gmail.com",
                Subject: "Welcome Back to Habit Tracker!",
                Body: `
                    <p>Hello ${userData.username},</p>
                    <p>Welcome back to the Habit Tracker!</p>
                    <p style="padding: 12px; border-left: 4px solid #d0d0d0; font-style: italic;">
                      We're thrilled to see you again today. Let’s make today productive and work towards your goals!
                    </p>
                    <p>Best regards,<br>The Habit Tracker Team</p>
                `
            }).then(function (message) {
                alert("Mail sent successfully!");
                // Optionally, redirect to another page
                window.location.href = 'Dashboard4.html';
            }).catch(function (error) {
                console.error("Failed to send email:", error);
                alert("Failed to send email. Please try again.");
            });

        } else if (response.status === 401) {
            alert('Authentication failed. Please check your username and password.');
        } else {
            const errorText = await response.text(); // Read response as text
            alert(errorText); // Display the error message
        }
    } catch (error) {
        console.error('Login failed:', error.message); // Log the error
        alert(error.message); // Show the error message to the user
    }
});
