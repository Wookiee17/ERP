import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useHistory, useParams } from "react-router-dom";

import TextField from "@material-ui/core/TextField";


import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import Button from "@material-ui/core/Button";
import Snackbar from "@material-ui/core/Snackbar";
<<<<<<< Updated upstream
import DescriptionIcon from "@material-ui/icons/Description";
import ClassIcon from '@mui/icons-material/Class';
import { Modal } from "react-bootstrap";
import TextField from "@material-ui/core/TextField";
import FormLabel from "@material-ui/core/FormLabel";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
<<<<<<< Updated upstream
import axios from "axios";
=======

import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
>>>>>>> Stashed changes
import { makeStyles } from "@material-ui/core/styles";
import { Dropdown, Option } from "./Dropdown";
import Demo from "../Components/timetable/demo"

import Request from "./Request/calendar"

const useStyles = makeStyles((theme) => ({
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "30ch",
    },
  },
  formControl: {
    margin: theme.spacing(1),
    //   width: '30ch',
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));

const AdminResult = () => {
<<<<<<< Updated upstream
  const classes = useStyles();
  const [show, setShow] = useState(false);
  const [show4, setShow4] = useState(false);
  const [show1, setShow1] = useState(false);
  const [show5, setShow5] = useState(false);
  const handleClose = () => setShow(false);
  const handleClose4 = () => setShow4(false);
  const handleClose5 = () => setShow5(false);
  const handleClose1 = () => setShow1(false);
  const handleShow = () => setShow(true);
  const handleShow1 = () => setShow1(true);
  const handleShow4 = () => setShow4(true);
  const handleShow5 = () => setShow5(true);
  const [studentdata, setClassid] = useState([]);
  const [subjectname, setsubjectname] = useState([]);
  const [sectiondata, setSectiondata] = useState([]);
  const [classid, setclassid] = useState("");
  const [sectionid, setSectionid] = useState("");
  // const [searchTerm, setSearchTerm] = useState('');
  const [subjectdata, setsubjectdata] = useState([]);
  const [city, setcity] = useState();
  const [country, setcountry] = useState();
  const [line1, setline1] = useState();
  const [line2, setline2] = useState();
  const [pinCode, setpinCode] = useState();
  const [state, setstate] = useState();
  const [isAddressPermanen, setisAddressPermanen] = useState();
=======
>>>>>>> Stashed changes
  

  const history = useHistory();
  
  
  const [messageinfo, setMessageinfo] = useState("");
=======

const AdminAttendance = () => {

  const [start, setStart]=useState();
  const [join, setJoin]=useState();
  const [messageinfo, setMessageinfo]=useState();
  const history = useHistory();
  const [imageUrl, setimageUrl]=useState();
  
>>>>>>> Stashed changes
  const [message, setMessage] = useState({
    open: false,
    vertical: "top",
    horizontal: "right",
  });
  const { vertical, horizontal, open } = message;
  const handleMessage = () => {
    setMessage({ open: true, vertical: "top", horizontal: "right" });
  };
  const CloseMessage = () => {
    setMessage({ ...message, open: false });
  };
  const logOut = () => {
    localStorage.clear();
    history.push("/");
  };
<<<<<<< Updated upstream
 
  var count = 0;
  return (
    <>
      <div class="dashboard">
      <div class="left">
          <div class="navigation">
            <div class="wrapper2">
            <div class="abilan">
                <img alt="Logo" src={"https://d2jg2pri5bpndu.cloudfront.net/schools/250_d2d38ff6daa4f91f7fbfd664553d068a.png"} />
              </div>
=======

  const data = {
    "topic": "The title of your zoom meeting",
   "type": 2,
    "start_time": "2019-06-14T10:21:57",
     "duration": "45",
      "timezone": "Asia/Calcutta",
       "agenda": "test",
        "recurrence": 
        {
          "type": 1, 
        "repeat_interval": 1 
      },
         "settings": 
         {
           "host_video": "true",
          "participant_video": "true",
           "join_before_host": "False",
            "mute_upon_entry": "False",
             "watermark": "true",
              "audio": "voip",
               "auto_recording": "cloud" 
              }
            }
  const sendData = () => {
    console.log("data",data);
   
       axios
         .post("http://erpcd-erpde-13vw78n5lwhpz-1518206987.ap-south-1.elb.amazonaws.com/erpdev/timetable/create-meeting",data,{
           headers: {
             'Authorization': `Bearer ${localStorage.getItem("access")}` 
           }
         })
         .then((response) => {
           console.log("aaa",response.data.responseBody)
           setJoin(response.data.responseBody.join_url);
           setStart(response.data.responseBody.start_url)
           console.log("join",join)
           console.log("create",start)
          //  setMessageinfo(error.response.data.message);
           handleMessage();
           refreshPage();
           
         })
         .catch((error) => {
           if (error.response) {
             setMessageinfo(error.response.data.message);
             handleMessage();
           }
         });
    
   };
   function refreshPage() {
    window.location.reload(false);
  }

  useEffect(() => {
    axios
    .get()
    .then(
      (response) => {
        console.log(response);
        // setAllData(response.data);
      },
      (error) => {
        setMessageinfo("Please fill all fields!");
        handleMessage();
      }
    );
  }, [])
  return (
    <>
      <div class="dashboard">
<div class="left">
    <div class="navigation">
      <div class="wrapper2">
      <div class="abilan">
          <img alt="Logo" src={"https://d2jg2pri5bpndu.cloudfront.net/schools/250_d2d38ff6daa4f91f7fbfd664553d068a.png"} />
        </div>
>>>>>>> Stashed changes

        <Link to="/campusdashboard" class="nav-link ">
          <div class="folder-icons ">
            <div class="icon1">
              <i class="fas  fa-columns active"></i>
            </div>
            <div class="icon-name1 active ">Dashboard</div>
          </div>
        </Link>
        <Link to="/admissioncomponents" class="nav-link ">
          <div class="folder-icons ">
            <div class="icon1">
              <i class="fas fa-school"></i>
            </div>
            <div class="icon-name1">Admission</div>
          </div>
        </Link>

        <Link class="nav-link" to="/class">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-users-class "></i>
            </div>
            <div class="icon-name ">Class</div>
          </div>
        </Link>

<<<<<<< Updated upstream
              <Link class="nav-link" to="/students">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-graduate"></i>
                  </div>
                  <div class="icon-name">Students</div>
                </div>
              </Link>
              <Link class="nav-link" to="/employee">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-tie"></i>
                  </div>
                  <div class="icon-name"> Employee</div>
                </div>
              </Link>
              <Link class="nav-link" to="/feecomponents">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-money-check-alt"></i>
                  </div>
                  <div class="icon-name">Fee</div>
                </div>
              </Link>
              <Link class="nav-link" to="/subject">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-graduate"></i>
                  </div>
                  <div class="icon-name">Subject</div>
                </div>
              </Link>
              <Link class="nav-link" to="/Timetable">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-graduate"></i>
                  </div>
                  <div class="icon-name">Time Table</div>
                </div>
              </Link>
              <Link class="nav-link" to="/adminresult">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-graduate"></i>
                  </div>
                  <div class="icon-name">Results</div>
                </div>
              </Link>
              <Link class="nav-link" to="/exam">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-calculator-alt"></i>
                  </div>
                  <div class="icon-name">Student exam</div>
                </div>
              </Link>
              <Link class="nav-link" to="/time">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-calculator-alt"></i>
                  </div>
                  <div class="icon-name">Timetable</div>
                </div>
              </Link>

              <Link class="nav-link" to="/AdminAttendance">
                <div class="folder-icons">
                  <div class="icon1">
                    <i class="fas fa-user-graduate"></i>
                  </div>
                  <div class="icon-name">Attendance</div>
                </div>
              </Link>
=======
        <Link class="nav-link" to="/students">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-graduate"></i>
>>>>>>> Stashed changes
            </div>
            <div class="icon-name">Students</div>
          </div>
<<<<<<< Updated upstream
        </div>
        <div class="right-side">
          <div class="right-header">
            <div class="top-bar">
              <div class="top-bar-justify">
                <div class="big-inbox">Result</div>
                <button onClick={logOut} class="btn text-bolder text-right">
                  Log Out
                </button>
              </div>
=======
        </Link>
        <Link class="nav-link" to="/employee">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-tie"></i>
>>>>>>> Stashed changes
            </div>
            <div class="icon-name"> Employee</div>
          </div>
<<<<<<< Updated upstream
          <div class="right-body">
<<<<<<< Updated upstream
            {/* back to dashboard option*/}
            
              <button
                  type="button"
                  onClick={handleShow}
                  class="btn btn-primary btn-lg"
                  style={{
                    margin: "0px",
                    marginLeft: "1350px",
                    fontSize: "15px",
                    fontWeight: "bold",
                  }}
                >
                  <AddIcon /> Add Result
                </button>
                <br/>
            

            <div class="message">
              <div class="add-student">
                 <Modal show={show} onHide={handleClose}>
                                    <Modal.Header closeButton>
                                        <Modal.Title>Add Result</Modal.Title>
                                    </Modal.Header>
                                    <Modal.Body>
                                        <div class="row billing-main">
                                            <div class="col-6 billing-box">
                                                <TextField className="pb-3 bg-white" type="text" onChange={(e) => setclassid(e.target.value)} label="Class" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="text" onChange={(e) => setclassid(e.target.value)} label="Exam" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="text" onChange={(e) => setsubjectname(e.target.value)} label="Student Name" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="number" onChange={(e) => setsubjectname(e.target.value)} label="English" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="number" onChange={(e) => setsubjectname(e.target.value)} label="Mathematics" variant="filled" />
                                                <TextField className="pb-3 bg-white"type="number" onChange={(e) => setsubjectname(e.target.value)} label="Hindi" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="number" onChange={(e) => setsubjectname(e.target.value)} label="SST" variant="filled" />
                                                <TextField className="pb-3 bg-white" type="number" onChange={(e) => setsubjectname(e.target.value)} label="EVS" variant="filled" />
                                                
                                               
                                            </div>
                                        </div>
                                    </Modal.Body>
                                    <Modal.Footer>
                                        <button class="btn btn-secondary" onClick={handleClose}>
                                            Close
                                            </button>
                                        <button  onClick={sendData} className="btn btn-primary">Create</button>
                                    </Modal.Footer>
                                </Modal> 
                                
                <Modal show={show5} onHide={handleClose5}>
                  <Modal.Header closeButton>
                    <Modal.Title>Update Students</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <div class="row billing-main">
                      <div class="col-6 billing-box">
                        
                        <TextField
                          className="pb-3"
                          type="text"
                          defaultValue={prevdata.last_name}
                          onChange={(e) => setsubjectname(e.target.value)}
                          label="Subject Name"
                          variant="filled"
                        />
                       
                      </div>
                      
                    </div>
                  </Modal.Body>
                  <Modal.Footer>
                    <button class="btn btn-secondary" onClick={handleClose5}>
                      Close
                    </button>
                    <button onClick={sendUpdated} className="btn btn-primary">
                      Update
                    </button>
                  </Modal.Footer>
                </Modal>
                <Modal show={show2} onHide={remove}>
                  <Modal.Header closeButton>
                    <Modal.Title>Confirmation</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <div className="row">
                      <div className="col-12">
                        <h2 className="text-center">
                          Are You Sure You Want To Delete?
                        </h2>
                      </div>
                    </div>
                  </Modal.Body>
                  <Modal.Footer>
                    <button class="btn btn-secondary" onClick={remove}>
                      Close
                    </button>
                    <button onClick={deleteData} className="btn btn-primary">
                      Yes
                    </button>
                  </Modal.Footer>
                </Modal>
                <Modal show={show3} onHide={remove1}>
                  <Modal.Header closeButton>
                    <Modal.Title>Change Password</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <div className="row billing-main">
                      <div className="col-8 billing-box">
                        <TextField
                          className="pb-3 bg-white"
                          type="password"
                          onChange={(e) => setPassword(e.target.value)}
                          label="Password"
                          variant="filled"
                        />
                        <TextField
                          className="pb-3"
                          type="password"
                          onChange={(e) => setConfirmpassword(e.target.value)}
                          label="Confirm Password"
                          variant="filled"
                        />
                      </div>
                    </div>
                  </Modal.Body>
                  <Modal.Footer>
                    <button class="btn btn-secondary" onClick={remove1}>
                      Close
                    </button>
                    <button
                      onClick={changePassword}
                      className="btn btn-primary"
                    >
                      Change
                    </button>
                  </Modal.Footer>
                </Modal>
              </div>
              <div className="row">
                <div className="col-6 text-left mt-1">
                  <TextField
                    className="pb-3 bg-white"
                    value={searchTerm}
                    type="text"
                    helperText="By Batch"
                    onChange={(e) => setSearchTerm(e.target.value)}
                    label="Search Result"
                  />
                  <button onClick={reset} className="btn btn-primary mt-3 ml-5">
                    Reset
                  </button>
                </div>
                <div className="col-6 text-right">
                  {/* <FormControl className={classes.formControl}>
                    <InputLabel id="demo-simple-select-label">Class</InputLabel>
                    <Select
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={classid}
                      onChange={(e) => setClassid(e.target.value)}
                    >
                      {classdata.map((val, i) => {
                        return (
                          <MenuItem value={val.id}>{`${val.name}`}</MenuItem>
                        );
                      })}
                    </Select>
                  </FormControl>
                  <button
                    onClick={search}
                    className="btn btn-primary mt-3 ml-1"
                  >
                    Search
                  </button> */}
                  {/* <FormControl className={classes.formControl}>
                    <InputLabel id="demo-simple-select-label">
                      Section
                    </InputLabel>
                    <Select
                      labelId="demo-simple-select-label"
                      id="demo-simple-select"
                      value={sectionid}
                      onChange={(e) => setSectionid(e.target.value.toString())}
                    >
                      {sectiondata.map((val, i) => {
                        return (
                          <MenuItem value={val.id}>{`${val.name}`}</MenuItem>
                        );
                      })}
                    </Select>
                  </FormControl> */}
                </div>
              </div>
              <div class="table-responsive">
                <table class="table no-wrap">
                  <thead>
                    <tr>
                    <th class="border-top-0">Exam</th>
                    
                      <th class="border-top-0">Class</th>
                      <th class="border-top-0">Student</th>
                      <th class="border-top-0">Marks(%)</th>
                      <th class="border-top-0">Added By</th>
                      <th class="border-top-0">Status</th>
                      <th class="border-top-0">Action</th>
                                          
                    </tr>
                  </thead>
                  <tbody>
                    
                    
                    {/* {subjectdata
                      
                      .filter((val) => {
                        if (searchTerm == "") {
                          return val;
                        } else if (
                          `${val.subjectName}` 
                            .toLowerCase()
                            .includes(searchTerm.toLowerCase())
                        ) {
                          return val;
                        }
                      })
                      
                      
                      .map((val, i) => { */}
                        {/* return ( */}
                          <tr key={i}>
                           
                            <td>
                            {`class 12 Exam 2021 `}
                             
                            </td>
                            <td>
                            {"class 12  2021"}
                             
                            </td>
                            <td>
                            {"RAM"}
                             
                            </td>
                            <td>
                            {"90.75%"}
                             
                            </td>
                             
                            <td>
                            {"Peter Parker"}
                             
                            </td>
                            <td>
                            {"Active"}
                             
                            </td>
                            
                            <td>
                              <ButtonGroup
                                disableElevation
                                variant="contained"
                                color="primary"
                              >
                                
                                <Button
                                  className="student-btn-up"
                                //   onClick={() => handleClick5(val.subjectId,val.classId)}
                                >
                                  <UpdateIcon className="text-white" />
                                </Button>
                                <Button
                                  className="student-btn-del"
                                //   onClick={() => handleClick(val.subjectId)}
                                >
                                  <DeleteIcon className="text-white" />
                                </Button>
                              </ButtonGroup>
                            </td>
                          </tr>
                        );
                      {/* }
                      )} */}
                  </tbody>
                </table>
=======
              <div class="message">
              
              
              <div class="table-responsive">
              <Demo/>
              
              
           
>>>>>>> Stashed changes
              </div>
=======
        </Link>
        <Link class="nav-link" to="/feecomponents">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-money-check-alt"></i>
>>>>>>> Stashed changes
            </div>
            <div class="icon-name">Fee</div>
          </div>
        </Link>
        <Link class="nav-link" to="/subject">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-graduate"></i>
            </div>
            <div class="icon-name">Subject</div>
          </div>
        </Link>
        <Link class="nav-link" to="/Timetable">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-graduate"></i>
            </div>
            <div class="icon-name">Time Table</div>
          </div>
        </Link>
        <Link class="nav-link" to="/adminresult">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-graduate"></i>
            </div>
            <div class="icon-name">Calendar</div>
          </div>
        </Link>
        <Link class="nav-link" to="/exam">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-calculator-alt"></i>
            </div>
            <div class="icon-name">Student Exam</div>
          </div>
        </Link>
       <Link class="nav-link" to="/adminresult">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-user-graduate"></i>
            </div>
            <div class="icon-name">Calendar</div>
          </div>
        </Link>
        <Link class="nav-link" to="/liveclass">
          <div class="folder-icons">
            <div class="icon1">
              <i class="fas fa-calculator-alt"></i>
            </div>
            <div class="icon-name">Live Class</div>
          </div>
        </Link> 
        
      </div>
    </div>
  </div>
  <div class="right-side">
    <div class="right-header">
      <div class="top-bar">
        <div class="top-bar-justify">
          <div class="big-inbox print-capitalize">
           
            Live Class
          </div>
          <button onClick={logOut} class="btn text-bolder text-right">
            Log Out
          </button>
        </div>
      </div>
      <hr class="new-hr" />
    </div>
    <div class="right-body">
    <h1>Create Meeting page</h1>
<br />

 
  <button onClick={sendData}  >
    CR
  </button>

<br />
<div>
<br /><a href={join} target="_blank">start_url:</a>
</div>
<br />
<br />
<div>
<br /><a href={start} target="_blank">join_url:</a> 
</div>
      <Snackbar
        anchorOrigin={{ vertical, horizontal }}
        open={open}
        autoHideDuration={4000}
        onClose={CloseMessage}
        message={messageinfo}
        key={vertical + horizontal}
      />
    </div>
  </div>
</div>


      <Snackbar
        anchorOrigin={{ vertical, horizontal }}
        open={open}
        autoHideDuration={4000}
        onClose={CloseMessage}
        message={messageinfo}
        key={vertical + horizontal}
      />
    </>
  );
};
export default AdminAttendance;
