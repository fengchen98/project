class LocalStorage {
    constructor(key, value, expire = 3600000){
        this.key = key
        this.value = value
        this.expire = expire
        this.setItem(this.key, this.value, this.expire)
    }
    setItem(key, value, expire){
        if(typeof value !== 'string'){
            value = JSON.stringify(value)
        }
        const _value = value + `|${parseInt(new Date().getTime()) +expire }`
        window.localStorage.setItem(key, _value)
    }
    getItem(key = this.key){
        const value = window.localStorage.getItem(key)
        const expire_date = value.split('|')[1]
        if(parseInt(new Date().getTime()) < parseInt(expire_date)){
            return JSON.parse(value.split('|')[0])
        }else {
            window.localStorage.removeItem(key)
            return new Error('数据已过期')
        }
    }
}
const person = new LocalStorage('name', '小明', 1611111000)
person.getItem()