import request from '@/utils/request'

export function onlineList() {
  return request({
    url: '/webapp/carousel/onlinelist',
    method: 'get'
  })
}

export function offlineList() {
  return request({
    url: '/webapp/carousel/offlineList',
    method: 'get'
  })
}

export function allList() {
  return request({
    url: '/webapp/carousel/allList',
    method: 'get'
  })
}

export function upload(type, data) {
  return request({
    url: `/webapp/carousel/upload/${type}`,
    method: 'post',
    data
  })
}

export function add(data) {
  return request({
    url: '/webapp/carousel/add',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: `/webapp/carousel/delete/${id}`,
    method: 'get'
  })
}

export function online(id) {
  return request({
    url: `/webapp/carousel/online/${id}`,
    method: 'get'
  })
}

export function offline(id) {
  return request({
    url: `/webapp/carousel/offline/${id}`,
    method: 'get'
  })
}
