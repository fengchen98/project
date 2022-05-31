import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/logistic-test-api/table/list',
    method: 'get',
    params
  })
}
