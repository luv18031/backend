import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

function Login() {

  const initialState = {
    name: "",
    // email: "",
    username: ""
  }

  const [eachEntry, setEachEntry] = useState(initialState)
  // const {name, email, username} = eachEntry

  const handleOnChange = e => {
      setEachEntry({...eachEntry, [e.target.name]: e.target.value})
  }

  const handleSubmit = e => {
    e.preventDefault();
    console.log("hello")
    fetch('http://localhost:8080/login', {
      method: "POST",
      body: JSON.stringify(eachEntry),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    }).then(
      (response) => (response.json())
    ).then((response) => {
      if(response.status === 'success'){
        alert("Message sent.");
        setEachEntry({...eachEntry, [e.target.name]: ''})
      } else if(response.status === 'fail'){
        alert("Message failed to send.")
      }
    })
  }


  return (
    <Form onSubmit={handleSubmit}>
      {/* <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control
        name='name'
        onChange={handleOnChange}
        type="email" placeholder="Enter email" />
        <Form.Text className="text-muted">
          We'll never share your email with anyone else.
        </Form.Text>
      </Form.Group> */}

      <Form.Group className="mb-3" controlId="formBasicUsername">
        <Form.Label>Username</Form.Label>
        <Form.Control 
        name='username'
        onChange={handleOnChange}
        type="text" placeholder="Enter Username" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control 
        name='password'
        onChange={handleOnChange}
        type="password" placeholder="Password" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Check me out" />
      </Form.Group>
      <Button 
      
      variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
}

export default Login;