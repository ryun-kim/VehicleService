{
    const homelogoElem = document.querySelector('#image_logo_home');

    if(homelogoElem){
        homelogoElem.addEventListener('click', ()=>{
            location.href = `/home`;
        });
    }

    const myFetch = {
        send: function(fetchObj, cb) {
            return fetchObj
                .then(res => res.json())
                .then(cb)
                .catch(e => { console.log(e) });
        },
        get: function(url, cb, param) {
            if(param) {
                const queryString = '?' + Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
                url += queryString;
            }
            return this.send(fetch(url), cb);
        },
        post: function(url, cb, param) {
            return this.send(fetch(url, {
                'method': 'post',
                'headers': { 'Content-Type': 'application/json' },
                'body': JSON.stringify(param)
            }), cb);
        },
        put: function(url, cb, param) {
            return this.send(fetch(url, {
                'method': 'put',
                'headers': { 'Content-Type': 'application/json' },
                'body': JSON.stringify(param)
            }), cb)
        },
        delete: function(url, cb) {
            return this.send(fetch(url, {
                'method': 'delete',
                'headers': { 'Content-Type': 'application/json' },
            }), cb);
        }
    }
}