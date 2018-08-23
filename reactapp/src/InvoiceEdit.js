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
import { hashHistory, Link } from 'react-router';
import $ from 'jquery';
import InvoiceList from './InvoiceList';
import history from './history';
import  './config';


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

var employeeNO;
var ID;
var IssueDate;
var Remark;
var Request;
var EmployeeID;
var RealName;

class InputAdornments extends React.Component {

    state = {
        ID: '',
        IssueDate: '',
        Remark:'',
        Request:'',
        EmployeeID:'',
        RealName:''
    };

    constructor(props) {
        super(props);
        console.log(this.props.match.params.employeeNO);
        employeeNO=this.props.match.params.employeeNO;
        ID=this.props.match.params.ID;
        console.log("id:"+ID);
        if (ID==undefined)    //New Request
        {
            ID =0;
        }
        else                   //get Data from Database
        {
            var datas = {'id':ID, 'employeeNO': null};
            var url =  global.constants.hosturl+"/InvoiceByIDJSON";
                $.ajax({
                    type:'POST',
                    url:url,
                    dataType : 'json',
                    data : datas,
                    async: false,   //同步
                    xhrFields: {
                        withCredentials: true
                    },
                    success : function(data) {
                        console.log(data);
                        ID = data.id;
                        IssueDate = data.creationDate;
                        Remark = data.remark;
                        Request = data.amount;
                        EmployeeID = data.employeeID;
                        RealName = data.realName;

                    },
                    error : function() {
                        $("#errorMsg").html("<span style='color: red;'>login Failed</span>");
                    }
                })
          }
          this.state.ID=ID;
          this.state.IssueDate=IssueDate;
          this.state.Remark=Remark;
          this.state.Request=Request;
          this.state.EmployeeID=EmployeeID;
          this.state.RealName=RealName;
    };



    handleChange = prop => event => {
        this.setState({ [prop]: event.target.value });


    };


    handleSave = event => {
        var creationDate= $("#adornment-IssueDate").val();
        var remark=$("#adornment-Remark").val();
        var amount=$("#adornment-RequestQuantity").val();
        var employeeID=EmployeeID;
        var invoiceid = ID;

        if (invoiceid == undefined || invoiceid =="undefined"){
            invoiceid = 0;
        }

        var url =  "http://192.168.0.160:8080/ssm_project/InvoiceSaveJSON";
        var datas = {'id' : invoiceid,'employeeID' : employeeID, 'creationDate' : creationDate,'remark':remark ,'amount': amount};

            $.ajax({
                type : 'POST',
                url : url,
                async : false,
                dataType : 'json',
                data : datas,
                xhrFields: {
                    withCredentials: true
                },

                success: function (data) {
                if (data.flag == 0) {
                    console.log("updated successed");
                    $("#errorMsg").html(data.message);
                    ReactDOM.render((<Router history={history}><Route path='/InvoiceList/:employeeNO'
                                                                      component={InvoiceList}/></Router>), document.getElementById('root'));
                    history.push('/InvoiceList/' + employeeNO);

                }
                else {
                    console.log("updated failed with some reasen:" + data.message);
                    $("#errorMsg").html("<span style='color: red;'>" + data.message + "</span>");
                }
            },
            error: function () {
                console.log("updated failed!");
                $("#errorMsg").html("<span style='color: red;'>Updated Failed</span>");
            }
        })
    }




    handleQuit = event => {
        window.close();
        //this.props.history.goBack();
    };

    render() {
        const { classes } = this.props;
        return (
            <div className="App">
                <header className="App-header">
                    <h1 className="App-title">Request Edit Page</h1>
                </header>
                        <div className={classes.root}  style={{textAlign: 'left',width: '300px', height: '500px', left: '50%', top: '50%',  position: 'absolute',backgroundColor: '#fff',transform: 'translate(-50%,-50%)' }}>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <InputLabel htmlFor="adornment-ID">ID</InputLabel>
                                <Input
                                    id="adornment-ID"
                                    value={this.state.ID}
                                />
                            </FormControl>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <InputLabel htmlFor="adornment-Request">Request</InputLabel>
                                <Input
                                    id="adornment-Request"
                                    value={this.state.RealName}
                                />
                            </FormControl>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <TextField
                                    id="adornment-IssueDate"
                                    label="Issue Date"
                                    type="date"
                                    defaultValue="1980-01-01"
                                    value={this.state.IssueDate}
                                    onChange={this.handleChange('IssueDate')}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </FormControl>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <TextField
                                    id="adornment-RequestQuantity"
                                    label="Request Quantity"
                                    type="number"
                                    defaultValue="0"
                                    value={this.state.Request}
                                    onChange={this.handleChange('Request')}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </FormControl>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <TextField
                                    id="adornment-Remark"
                                    label="Remark"
                                    multiline
                                    className={classes.inputMultiline}
                                    value={this.state.Remark}
                                    onChange={this.handleChange('Remark')}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                />
                            </FormControl>
                            <FormControl className={classNames(classes.margin, classes.textField)}>
                                <Button id="btnSubmitted"variant="contained" color="primary" className={classes.button}   onClick={this.handleSave}>
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

