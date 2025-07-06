import video from './../video/home-head-video.mp4';
import styles from "./MusicDistribution.module.css";

function MusicDistribution(){
    return (
        <div className={styles["main-container"]}>
            <video autoPlay muted loop className={styles["video"]}>
                <source src={video} type="video/mp4"/>
            </video>
            <div className={styles["music-distribution-container"]}>
                <div className={styles["heading"]} >
                    <span >Free </span>
                    <span>Music </span>
                    <span>Distribution</span>
                </div>
                <div className={styles["sub-heading"]}>
                    You can select the stores as per your wish
                </div>
                <div className={styles["paragraph"]}>
                    We are the fastest growing music distribution company in Asia Pacific. We are distributing content of Independent artists and Music labels globally.
                </div>
                <div className={styles["terms"]}>
                    *Free services are available based on content quality and user authenticity.
                </div>

                <div className={styles["join-button"]} >
                    Join Now
                </div>
            </div>
        </div>
    )
}

export default MusicDistribution;