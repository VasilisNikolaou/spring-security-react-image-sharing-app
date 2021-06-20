import { useState } from "react";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

const Signup = ({open, onClose}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");



    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>New Account</DialogTitle>
            <form>
                <TextField id="outlined-basic" variant="outlined" value={username} onChange={e => setUsername(e.target.value)} />
                <TextField id="outlined-basic" variant="outlined" value={password} onChange={e => setPassword(e.target.value)} />
                <TextField id="outlined-basic" variant="outlined" value={firstName} onChange={e => setFirstName(e.target.value)} />
                <TextField id="outlined-basic" variant="outlined" value={lastName} onChange={e => setLastName(e.target.value)} />
                <TextField id="outlined-basic" variant="outlined" value={email} onChange={e => setEmail(e.target.value)} />
                <Button variant="contained" color="primary">
                    Sign up
                </Button>
            </form>
        </Dialog>
    );
};


export default Signup;