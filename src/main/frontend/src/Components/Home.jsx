import Matrics from "./Matrics";
import MusicDistribution from "./MusicDistribution";
import Navbar from "./Navbar";

function Home(){
    return (
        <div>
            <Navbar />
            <MusicDistribution />  
            <Matrics />
        </div>
    )
}

export default Home;