import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route} from 'react-router';
import './index.css';
import App from './Login';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
/*
ReactDOM.render(<Router >
    <Route path="/" component={App}/>
</Router>, document.getElementById('root'));
*/
registerServiceWorker();
