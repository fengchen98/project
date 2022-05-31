import request from '@/utils/request'

export function getRecipient() {
  return request({
    url: '/webapp/recipient/list',
    method: 'get'
  })
}

export function setDefaultRecipient(id) {
  return request({
    url: `/webapp/recipient/setDefault/${id}`,
    method: 'post'
  })
}

export function saveRecipient(data) {
  return request({
    url: `/webapp/recipient/save`,
    method: 'post',
    data: data
  })
}

export function removeRecipient(id) {
  return request({
    url: `/webapp/recipient/remove/${id}`,
    method: 'post'
  })
}

export function updateRecipient(data) {
  return request({
    url: `/webapp/recipient/update`,
    method: 'post',
    data
  })
}
