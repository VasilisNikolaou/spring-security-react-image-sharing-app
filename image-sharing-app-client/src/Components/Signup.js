import { useState } from "react";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import { makeStyles } from '@material-ui/core';
import { signup } from './utils/APIUtils.js';
import {
    USERNAME_MIN_LENGTH,
    USERNAME_MAX_LENGTH,
    PASSWORD_MIN_LENGTH,
    PASSWORD_MAX_LENGTH,
    FIRSTNAME_MIN_LENGTH,
    FIRSTNAME_MAX_LENGTH,
    LASTNAME_MIN_LENGTH,
    LASTNAME_MAX_LENGTH,
    EMAIL_MIN_LENGTH,
    EMAIL_MAX_LENGTH
} from './constants/constants.js';


const useStyles = makeStyles({
    iconButton: {
        position: 'absolute',
        right: '10px',
        top: '12px'
    }
})

const Signup = ({ open, onClose }) => {
    const [username, setUsername] = useState({ value: '' });
    const [password, setPassword] = useState({ value: '' });
    const [firstName, setFirstName] = useState({ value: '' });
    const [lastName, setLastName] = useState({ value: '' });
    const [email, setEmail] = useState({ value: '' });

    const classes = useStyles();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Submitting data to server ...');

        const signupRequest = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email
        };

        try {
            const response = await signup(signupRequest);
            console.log(response);
        } catch (error) {
            //handle error
        }
    }

    const handleInputChange = (event, validationFunction) => {
        const name = event.target.name;
        const value = event.target.value;
        const result = validationFunction(value);

        if (name === 'username') {
            setUsername({ value: value, ...result });
        } else if (name === 'password') {
            setPassword({ value: value, ...result });
        } else if (name === 'firstName') {
            setFirstName({ value: value, ...result });
        } else if (name === 'lastName') {
            setLastName({ value: value, ...result });
        } else {
            setEmail({ value: value, ...result });
        }

    }

    const isFormInvalid = () => {
        return !(username.errorMsg === '' &&
            password.errorMsg === '' && firstName.errorMsg === '' &&
            lastName.errorMsg === '' && email.errorMsg === '');
    }

    return (
        <Dialog open={open}>
            <DialogTitle>New Account</DialogTitle>
            <IconButton className={classes.iconButton} onClick={onClose}>
                <CloseIcon />
            </IconButton>
            <DialogContent>
                <DialogContentText>
                    Create an account fast and easy
                </DialogContentText>
                <TextField type="text"
                    margin="dense"
                    fullWidth
                    placeholder="Username"
                    variant="outlined"
                    name="username"
                    value={username.value}
                    onChange={(event) => handleInputChange(event, validateUsername)}
                    error={username.errorMsg ? username.errorMsg !== '' : false}
                    helperText={username.errorMsg && username.errorMsg} />
                <TextField
                    type="password"
                    margin="dense"
                    fullWidth
                    placeholder="Password"
                    variant="outlined"
                    name="password"
                    value={password.value}
                    onChange={(event) => handleInputChange(event, validatePassword)}
                    error={password.errorMsg ? password.errorMsg !== '' : false}
                    helperText={password.errorMsg && password.errorMsg} />
                <TextField
                    type="text"
                    margin="dense"
                    fullWidth
                    placeholder="First Name"
                    variant="outlined"
                    name="firstName"
                    value={firstName.value}
                    onChange={(event) => handleInputChange(event, validateFirstName)}
                    error={firstName.errorMsg ? firstName.errorMsg !== '' : false}
                    helperText={firstName.errorMsg && firstName.errorMsg} />
                <TextField
                    type="text"
                    margin="dense"
                    fullWidth
                    placeholder="Last Name"
                    variant="outlined"
                    name="lastName"
                    value={lastName.value}
                    onChange={(event) => handleInputChange(event, validateLastName)}
                    error={lastName.errorMsg ? lastName.errorMsg !== '' : false}
                    helperText={lastName.errorMsg && lastName.errorMsg} />
                <TextField
                    type="text"
                    margin="dense"
                    fullWidth
                    placeholder="Email"
                    variant="outlined"
                    name="email"
                    value={email.value}
                    onChange={(event) => handleInputChange(event, validateEmail)}
                    error={email.errorMsg ? email.errorMsg !== '' : false}
                    helperText={email.errorMsg && email.errorMsg} />
            </DialogContent>
            <DialogActions>
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    size="large"
                    onClick={handleSubmit}
                    disabled={isFormInvalid()}
                    fullWidth>
                    Sign up
                </Button>
            </DialogActions>
        </Dialog>
    );
};


const validateUsername = (username) => {
    if (isEmpty(username)) {
        return {
            errorMsg: 'username cannot be empty'
        }
    } else if (username.length < USERNAME_MIN_LENGTH || username.length > USERNAME_MAX_LENGTH) {
        return {
            errorMsg: `username must be between ${USERNAME_MIN_LENGTH} and ${USERNAME_MAX_LENGTH}`
        }
    }

    return {
        errorMsg: ''
    }
}

const validateFirstName = (firstName) => {
    if (isEmpty(firstName)) {
        return {
            errorMsg: 'first name cannot be empty'
        }
    } else if (firstName.length < FIRSTNAME_MIN_LENGTH || firstName.length > FIRSTNAME_MAX_LENGTH) {
        return { errorMsg: `first name must be between ${FIRSTNAME_MIN_LENGTH} and ${FIRSTNAME_MAX_LENGTH}` }
    }

    return {
        errorMsg: ''
    }
}

const validatePassword = (password) => {
    if (isEmpty(password)) {
        return { errorMsg: 'password cannot be empty' }
    } else if (password.length < PASSWORD_MIN_LENGTH || password.length > PASSWORD_MAX_LENGTH) {
        return { errorMsg: `password must be between ${PASSWORD_MIN_LENGTH} and ${PASSWORD_MAX_LENGTH}` }
    }

    return {
        errorMsg: ''
    }
}

const validateLastName = (lastName) => {
    if (isEmpty(lastName)) {
        return { errorMsg: 'last name cannot be empty' }
    } else if (lastName.length < LASTNAME_MIN_LENGTH || lastName.length > LASTNAME_MAX_LENGTH) {
        return { errorMsg: `last name must be between ${LASTNAME_MIN_LENGTH} and ${LASTNAME_MAX_LENGTH}` }
    }

    return {
        errorMsg: ''
    }
}

const validateEmail = (email) => {
    if (isEmpty(email)) {
        return { errorMsg: 'email cannot be empty' }
    } else if (email.length < EMAIL_MIN_LENGTH || email.length > EMAIL_MAX_LENGTH) {
        return { errorMsg: `email must be between ${EMAIL_MIN_LENGTH} and ${EMAIL_MAX_LENGTH}` }
    }

    return {
        errorMsg: ''
    }
}

function isEmpty(str) {
    return (!str || str.length === 0);
}

export default Signup;