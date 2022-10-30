import { CssBaseline } from "@mui/material";
import "./App.css";
import Header from "./component/Header";
import Sidebar from "./component/Sidebar";
// import Editor from './component/Editor/Editor';
import Test from "./component/Test";

function App() {
  return (
    <div className="App">
      <CssBaseline />
      {/* <Editor/> */}
      <Header />
      <Test />
      <Sidebar />
    </div>
  );
}

export default App;
