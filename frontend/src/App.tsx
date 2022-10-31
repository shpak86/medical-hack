import { CssBaseline } from "@mui/material";
import "./App.css";
import Sidebar from "./component/Sidebar";
// import Editor from './component/Editor/Editor';
import Editor from "./component/Editor/Editor";

function App() {
  return (
    <div className="App">
      <CssBaseline />
      {/* <Editor/> */}
      <Sidebar>
        <Editor />
      </Sidebar>
    </div>
  );
}

export default App;
