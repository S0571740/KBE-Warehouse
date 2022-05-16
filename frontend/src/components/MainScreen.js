import React, {useState} from "react";
import Button from "./Button";

const MainScreen = ({components}) => {

    const [allProductComponentsAreShown, setAllProductComponentsAreShown] = useState(false)

    return (

        <div>
            <div className="header">
                <h1>Guitarbuilder</h1>
                <Button text={'log out'}/>
            </div>
            <Button text={'Unsere Produkte'}/>
            <Button text={'Alle Produktkomponenten anzeigen'} onClick={() => {
                setAllProductComponentsAreShown(true)
            }}/>
            <Button text={'Meine Gitarren'}/>
            <Button text={'Währung'}/>

            {allProductComponentsAreShown && <p>{components}</p>}

        </div>
    )
}

export default MainScreen