
const fetchPublicKey = async () => {
    return (await fetch('/keys/public')).text();
}


