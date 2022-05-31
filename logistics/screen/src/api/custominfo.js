import request from '@/utils/request'

export function getCustomInfo(token) {
  return request({
    url: '/logistic-test-api/custominfo/list',
    method: 'get',
    params: { token }
  })
}
