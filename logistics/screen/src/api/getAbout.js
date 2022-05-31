import request from '@/utils/request'

export function getAbout(token) {
  return request({
    url: '/logistic-test-api/getAbout',
    method: 'get',
    params: { token }
  })
}
