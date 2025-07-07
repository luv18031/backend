function Hello(){
    return (
        <div>
            <h1>thymeleaf</h1>
            <form action="/logout" method="post">
                <input type="submit" value="Sign Out"/>
            </form>
        </div>
    )
}

export default Hello;