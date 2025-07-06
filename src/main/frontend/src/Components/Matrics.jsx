import styles from "./Matrics.module.css";


function Matrics(){
    return (
        <div className={styles["main-container"]} >
            <div className={styles["container"]}>
                <div className={styles["icon"]}>
                    <svg xmlns="http://www.w3.org/2000/svg" height="100px" viewBox="0 -960 960 960" width="100px" fill="#1f1f1f"><path d="M80-80v-720q0-33 23.5-56.5T160-880h440q33 0 56.5 23.5T680-800v17q-24 11-44 27t-36 36v-80H160v527l47-47h393v-160q16 20 36 36t44 27v97q0 33-23.5 56.5T600-240H240L80-80Zm160-320h160v-80H240v80Zm520-80q-50 0-85-35t-35-85q0-50 35-85t85-35q11 0 21 2t19 5v-207h160v80h-80v240q0 50-35 85t-85 35Zm-520-40h280v-80H240v80Zm0-120h280v-80H240v80Zm-80 320v-480 480Z"/></svg>
                </div>
                <div className={styles["number"]}>
                    772997+
                </div>
                <div className={styles["description"]}>
                    Total Music tracks Distributed Worldwide
                </div>
            </div>
            <div className={styles["container"]}>
                <div className={styles["icon"]}>
                    <svg xmlns="http://www.w3.org/2000/svg" height="100px" viewBox="0 -960 960 960" width="100px" fill="#1f1f1f"><path d="M160-160q-33 0-56.5-23.5T80-240v-480q0-33 23.5-56.5T160-800h480q33 0 56.5 23.5T720-720v180l160-160v440L720-420v180q0 33-23.5 56.5T640-160H160Zm0-80h480v-480H160v480Zm0 0v-480 480Z"/></svg>
                </div>
                <div className={styles["number"]}>
                    45455+
                </div>
                <div className={styles["description"]}>
                    Total Music tracks Distributed Worldwide
                </div>
            </div>
            <div className={styles["container"]}>
                <div className={styles["icon"]}>
                    <svg xmlns="http://www.w3.org/2000/svg" height="100px" viewBox="0 -960 960 960" width="100px" fill="#1f1f1f"><path d="M480-400q-50 0-85-35t-35-85v-240q0-50 35-85t85-35q50 0 85 35t35 85v240q0 50-35 85t-85 35Zm0-240Zm-40 520v-123q-104-14-172-93t-68-184h80q0 83 58.5 141.5T480-320q83 0 141.5-58.5T680-520h80q0 105-68 184t-172 93v123h-80Zm40-360q17 0 28.5-11.5T520-520v-240q0-17-11.5-28.5T480-800q-17 0-28.5 11.5T440-760v240q0 17 11.5 28.5T480-480Z"/></svg>
                </div>
                <div className={styles["number"]}>
                    5621+
                </div>
                <div className={styles["description"]}>
                    Total Music tracks Distributed Worldwide
                </div>
            </div>
        </div>
    )
}

export default Matrics;