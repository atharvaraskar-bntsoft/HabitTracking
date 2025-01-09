document.querySelector('form').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="psw"]').value;

    try {
        const response = await fetch('http://localhost:8080/habittrackerapi/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        if (response) {
            const userData = await response.json(); // Handle successful authentication response
           // alert(`Welcome, ${userData.username}!`); // Show a welcome message
           console.log(userData);
           console.log("user_id",userData.id);
           localStorage.setItem("USERID",userData.id)
           alert(`Welcome, ${userData.username}!`); // Show a welcome message
            // Optionally, you can redirect the user to another page
            window.location.href = 'Dashboard4.html';
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
