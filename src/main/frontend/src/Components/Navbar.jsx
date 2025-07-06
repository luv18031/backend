

function Navbar(){
    return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Logo</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Sell Your Music</a>
                </li>
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Services
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <li><a class="dropdown-item" href="#">Music Distribution</a></li>
                    <li><a class="dropdown-item" href="#">Video Distribution</a></li>
                    <li><a class="dropdown-item" href="#">Lyrics Distribution</a></li>
                    <li><a class="dropdown-item" href="#">Caller Tune Distribution</a></li>
                    <li><a class="dropdown-item" href="#">Music Publishing</a></li>
                </ul>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Artist Help</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Blogs</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="#">Jobs</a>
                </li>
                <li class="nav-item login-btn">
                <a class="nav-link" href="/login">Login</a>
                </li>
            </ul>
            </div>
        </div>
        </nav>
    )
}

export default Navbar;