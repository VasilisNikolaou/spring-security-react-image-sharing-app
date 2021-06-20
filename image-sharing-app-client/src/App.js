import { makeStyles, Paper } from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Login from "./Components/Login";

const useStyles = makeStyles({
  root: {
    minHeight: '60vh'
  },
  paper: {
    width: '370px'
  }
});

function App() {
  const classes = useStyles();
  return (
    <Grid container
      alignItems="center"
      justify="space-evenly"
      direction="row"
      className={classes.root}>

      <Grid item xs={12} md={6} lg={3}>
        <Typography variant="h1" color="primary">
          PiShare
        </Typography>
        <Typography variant="h6" color="textSecondary">
          Share your images NOW and <br /> &emsp; ...inspire the World!
        </Typography>
      </Grid>

      <Grid item xs={12} md={6} lg={3}>
        <Paper elevation={3} className={classes.paper}>
          <Login />
        </Paper>
      </Grid>

    </Grid>
  );
}

export default App;
