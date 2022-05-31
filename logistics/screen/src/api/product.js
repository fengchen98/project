import request from '@/utils/request'

export function getProductInfo(id) {
  return request({
    url: `/webapp/product/getinfo/${id}`,
    method: 'get'
  })
}

export function getProduct() {
  return request({
    url: '/webapp/product/list',
    method: 'get'
  })
}

export function getOnSaleProduct() {
  return request({
    url: '/webapp/product/onSaleList',
    method: 'get'
  })
}

export function getOffSaleProduct() {
  return request({
    url: '/webapp/product/offSaleList',
    method: 'get'
  })
}

// export function getAllProduct() {
//   return request({
//     url: '/webapp/product/alllist',
//     method: 'get'
//   })
// }

export function getDelProduct() {
  return request({
    url: '/webapp/product/delList',
    method: 'get'
  })
}

//  传入product实例对象
export function saveProduct(data) {
  return request({
    url: '/webapp/product/save',
    method: 'post',
    data
  })
}

//  传入product实例对象
export function updateProduct(data) {
  return request({
    url: '/webapp/product/update',
    method: 'post',
    data
  })
}

//  传入p删除的商品id列表
export function delProduct(ids) {
  return request({
    url: '/webapp/product/delete',
    method: 'post',
    data: ids
  })
}

//  上架商品
export function onSale(ids) {
  return request({
    url: '/webapp/product/onSale',
    method: 'post',
    data: ids
  })
}

//  下架商品
export function offSale(ids) {
  return request({
    url: '/webapp/product/offSale',
    method: 'post',
    data: ids
  })
}
