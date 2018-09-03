import React from 'react';
import ReactDOM from 'react-dom';
import {Router,Route } from 'react-router'

import logo from './logo.svg';
import './Login.css';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';

import $ from 'jquery';
import InvoiceList from './InvoiceList';
import  './config';
import {createBrowserHistory, createHashHistory, createMemoryHistory} from 'history'

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        fontsize: 'large',
    },
    margin: {
        margin: theme.spacing.unit,
    },
    withoutLabel: {
        marginTop: theme.spacing.unit * 3,
    },
    textField: {
        flexBasis: 200,
    },
    button: {
        margin: theme.spacing.unit,
    },
    input: {
        display: 'none',
    },

});

const history = createBrowserHistory()



class InputAdornments extends React.Component {
    state = {
        employeeno: '',
        password: '',
    };

    handleChange = prop => event => {
        this.setState({ [prop]: event.target.value });
    };

    handleMouseDownPassword = event => {
        event.preventDefault();
    };

    handleClickShowPassword = () => {
        this.setState(state => ({ showPassword: !state.showPassword }));
    };

    handleLogin = event => {
        var employeeNO = $("#adornment-employeeno").val();
        var password=$("#adornment-password").val();
        var url =  global.constants.hosturl+"/loginJSON";
        var datas = {'EmployeeNO' : employeeNO,'Password':password};
        console.log("parameters:"+datas);

        $.ajax({
            type:'POST',
            url:url,
            dataType : 'json',
            data : datas,
            async: false,
            xhrFields: {
                withCredentials: true
            },
            success : function(data) {
                if (data.flag == 0) {
                    console.log("login successed");
                    $("#errorMsg").html(data.message);

                   // window.location.href='./InvoiceList';

                    ReactDOM.render(( <Router history={history}><Route path='/InvoiceList/:employeeNO' component={InvoiceList}/></Router>), document.getElementById('root'));
                    //history.push('/InvoiceList/'+employeeNO);
                    history.push('/InvoiceList/'+employeeNO);
                    //window.location.href="invoiceList.html";
                   // this.props.history.push("/InvoiceList");
                   //context.router.push("./InvoiceList");
                }
                else {
                    console.log("login failed with some reasen:"+data.message);
                    $("#errorMsg").html("<span style='color: red;'>"+data.message+"</span>");
                }
            },
            error : function() {
                console.log("login failed!");
                $("#errorMsg").html("<span style='color: red;'>login Failed</span>");
            }
        })



         /* fetch function didn't to transfer the parameter
        let formData = new FormData();
        formData.append("EmployeeNO",employeeNO);
        formData.append("Password",password);

        fetch(url, {
            method: 'POST',
            dataType : 'json',
            body: datas,
             headers:{
              'Content-type':'application/json'
           },
            xhrFields: {
                withCredentials: true
            }
        }).then(res => res.json())
            .catch(error => console.error('Error:', error))
            .then(response => console.log('Success:', response));
       */
    };



    handleQuit = event => {
        window.close();
    };

    render() {
        const { classes } = this.props;

        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h1 className="App-title">Management System</h1>
                </header>
                        <div className={classes.root}  style={{textAlign: 'left',width: '300px', height: '350px', left: '50%', top: '50%',  position: 'absolute',backgroundColor: '#fff',transform: 'translate(-50%,-50%)' }}>

                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <InputLabel htmlFor="adornment-employeeno">Employee NO</InputLabel>
                                <Input
                                    id="adornment-employeeno"
                                    value={this.state.amount}
                                    onChange={this.handleChange('employeeno')}
                                />
                            </FormControl>

                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <InputLabel htmlFor="adornment-password">Password</InputLabel>
                                <Input
                                    id="adornment-password"
                                    type={this.state.showPassword ? 'text' : 'password'}
                                    value={this.state.password}
                                    onChange={this.handleChange('password')}
                                    endAdornment={
                                        <InputAdornment position="end">
                                            <IconButton
                                                aria-label="Toggle password visibility"
                                                onClick={this.handleClickShowPassword}
                                                onMouseDown={this.handleMouseDownPassword}
                                            >
                                              {this.state.showPassword ? <VisibilityOff /> : <Visibility />}
                                            </IconButton>
                                        </InputAdornment>
                                    }
                                />
                            </FormControl>

                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <Button id="btnSubmitted"variant="contained" color="primary" className={classes.button}   onClick={this.handleLogin}>
                                    Submit
                                </Button>
                                <Button variant="contained" color="secondary" className={classes.button} onClick={this.handleQuit}>
                                    Quit
                                </Button>
                            </FormControl>
                            <div id="errorMsg">
                            </div>
                        </div>
            </div>


        );
    }
}

InputAdornments.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(InputAdornments);