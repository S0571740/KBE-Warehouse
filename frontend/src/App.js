import React, {useState, useEffect} from 'react';
import MainScreen from "./components/MainScreen";

const App = () => {
  const [components, setComponents] = useState("");

  useEffect(() => {
    fetch('/components')
        .then(response => response.text())
        .then(components => {
            setComponents(components);
        });
  },[])
  return (
      <div>
          <MainScreen components={components}/>
      </div>
  )
}

export default App;