import { makeStyles } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import Signup from './Signup';
import {useState} from 'react';

const useStyles = makeStyles({
    root: {
        margin: '0px',
        padding: '0px',
        textAlign: 'center'
    },
    formfield: {
        width: '330px',
        padding: '10px',
        margin: '5px'
    }
})

const Login = () => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);
    
    const handleClose = () => {
        setOpen(false);
    }

    return (
        <form className={classes.root}>
            <TextField id="outlined-basic" variant="outlined" className={classes.formfield} />
            <TextField id="outlined-basic" variant="outlined" className={classes.formfield} />
            <Button variant="contained" color="primary" className={classes.formfield}>
                Login
            </Button>
            <Divider />
            <Button variant="contained" color="primary" className={classes.formfield} onClick={e => setOpen(true)}>
                Create new account
            </Button>
            <Signup open={open} onClose={handleClose} />
        </form>
    )
}

export default Login;