import React from 'react';
import ReactDOM from 'react-dom';
import {Router,Route } from 'react-router'
import logo from './logo.svg';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import $ from 'jquery';
import InvoiceEdit from './InvoiceEdit';
import history from './history';
import  './config';



const CustomTableCell = withStyles(theme => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
    row: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.background.default,
        },
    },
});

let id = 0;
function createData(id, employeeID, realName, creationDate, remark , employeeNO) {

    return { id, employeeID, realName, creationDate, remark , employeeNO };
}

/*
const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];
rows.push( createData('cc', 356, 16.0, 49, 3.9),);
*/


const rows = [];
var realName;
var employeeID;
var employeeNO;
var prop1;
var prop2;
class CustomizedTable extends React.Component {

    constructor(props) {
        super(props);
        console.log(this.props.match.params.employeeNO);
        $.ajax({
            type : 'POST',
            url : global.constants.hosturl+"/GetLoginUser.do",
            async : false,
            dataType : 'json',
            xhrFields: {
                withCredentials: true
            },
            success : function(data) {

                console.log(data.valueOf());
                console.log(data);

                //document.getElementById('welcomeMsg').innerHTML='Welcome '+data.realName;
                //$("#welcomeMsg").append('<H1>Welcome '+data.realName+'</H1>');
                realName=data.realName;
                employeeNO=data.employeeNO;
                employeeID=data.id;
                console.log(employeeNO);
            },
            error : function() {
                alert("error!");
            }
        })
//GetDataByLoginuser
        var url =  global.constants.hosturl+"/InvoiceMainJSON.do";
        var datas = {};
        $.ajax({
            type : 'POST',
            url : url,
            async : false,
            dataType : 'json',
            data : datas,
            xhrFields: {
                withCredentials: true
            },
            success : function(data) {
                rows.splice(0,rows.length);   //removeaall for array, if not, history data will be remained.
                if (data.length > 0 ) {
                    $(data).each(function(){//重新生成
                        console.log(this);
                        rows.push( createData(this.id, this.employeeID, this.realName, this.creationDate, this.remark , this.employeeNO),);
                    })
                }
                else {
                    console.log("no data found");
                }
            },
            error : function() {
                console.log("error");
            }
        })

    }

    handleEdit = (prop1,prop2) => event => {
        console.log("prop:"+prop1);
        console.log("ID:"+prop2);
        ReactDOM.render(( <Router history={history}><Route path='/InvoiceEdit/:employeeNO/:ID' component={InvoiceEdit}/></Router>), document.getElementById('root'));
        history.push('/InvoiceEdit/'+prop1+"/"+prop2);
    };

    handleNew  = event => {

        //console.log(event);
        ReactDOM.render(( <Router history={history}><Route path='/InvoiceEdit/:employeeNO' component={InvoiceEdit}/></Router>), document.getElementById('root'));
        history.push('/InvoiceEdit/'+employeeNO);
    };

    render()
    {
        const {classes} = this.props;
        return (
            <Paper className={classes.root}>
                <div id="welcomeMsg">Welcome {realName}
                </div>
                <div>
                    <Button id="btnSubmitted" variant="contained" color="secondary"
                            className={classes.button} onClick={this.handleNew}>
                        New Request
                    </Button>
                </div>
                <Table className={classes.table}>
                    <TableHead>
                        <TableRow>
                            <CustomTableCell numeric>ID</CustomTableCell>
                            <CustomTableCell numeric>employeeID</CustomTableCell>
                            <CustomTableCell>Real Name</CustomTableCell>
                            <CustomTableCell>CreationDate</CustomTableCell>
                            <CustomTableCell>Remark</CustomTableCell>
                            <CustomTableCell>Edit</CustomTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map(row => {
                            return (
                                <TableRow className={classes.row} key={row.id}>
                                    <CustomTableCell component="th" scope="row" numeric>
                                        {row.id}
                                    </CustomTableCell>
                                    <CustomTableCell numeric>{row.employeeID}</CustomTableCell>
                                    <CustomTableCell>{row.realName}</CustomTableCell>
                                    <CustomTableCell>{row.creationDate}</CustomTableCell>
                                    <CustomTableCell>{row.remark}</CustomTableCell>
                                    <CustomTableCell>
                                        <Button id="btnSubmitted" variant="contained" color="primary"
                                                className={classes.button}  onClick={this.handleEdit(row.employeeNO,row.id)}>
                                            Edit
                                        </Button>
                                    </CustomTableCell>
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </Paper>


        );
    }
}



CustomizedTable.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CustomizedTable);