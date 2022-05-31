import request from '@/utils/request'

export function orderStatus(index) {
  const textArr = ['未支付', ' 已支付', '已取消']
  return textArr[index]
}

export function createOrder(data) {
  return request({
    url: '/webapp/order/create_order',
    // url: '/webapp/order/testOrder',
    method: 'post',
    data
  })
}
export function createRechangeOrder(data) {
  return request({
    url: '/webapp/order/create_Recharge_Order',
    method: 'post',
    data
  })
}

export function queryOrder(orderNumber) {
  return request({
    url: '/webapp/order/queryOrder',
    method: 'post',
    data: orderNumber
  })
}

export function cancelOrder(orderNumber) {
  return request({
    url: '/webapp/order/cancelOrder',
    method: 'post',
    data: orderNumber
  })
}

export function queryOrderDetail(orderNumber) {
  return request({
    url: `/webapp/order/queryOrderDetail`,
    method: 'post',
    data: orderNumber
  })
}

export function queryUserOrders() {
  return request({
    url: '/webapp/order/queryUserOrders',
    method: 'post'
  })
}

export function payOrder(orderNmber) {
  return request({
    url: `/alipay/pay/${orderNmber}`,
    method: 'post'
  })
}

export function allOrder() {
  return request({
    url: `/webapp/order/allOrder`,
    method: 'post'
  })
}
