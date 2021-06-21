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

const useStyles = makeStyles({
    iconButton: {
        position: 'absolute',
        right: '10px',
        top: '12px'
    }
})

const Signup = ({ open, onClose }) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");

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
                <TextField type="text" margin="dense" fullWidth placeholder="Username" variant="outlined" value={username} onChange={e => setUsername(e.target.value)} />
                <TextField type="password" margin="dense" fullWidth placeholder="Password" variant="outlined" value={password} onChange={e => setPassword(e.target.value)} />
                <TextField type="text" margin="dense" fullWidth placeholder="First Name" variant="outlined" value={firstName} onChange={e => setFirstName(e.target.value)} />
                <TextField type="text" margin="dense" fullWidth placeholder="Last Name" variant="outlined" value={lastName} onChange={e => setLastName(e.target.value)} />
                <TextField type="text" margin="dense" fullWidth placeholder="Email" variant="outlined" value={email} onChange={e => setEmail(e.target.value)} />
            </DialogContent>
            <DialogActions>
                <Button type="submit" variant="contained" color="primary" size="large" onClick={handleSubmit} fullWidth>
                    Sign up
                </Button>
            </DialogActions>
        </Dialog>
    );
};


export default Signup;