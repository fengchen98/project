import request from '@/utils/request'

export function get_cartstuff() {
  return request({
    url: '/webapp/cart/list',
    method: 'get'
  })
  // return new Promise((resolve, reject) => {
  //   const cartLists = [
  //     {
  //       id: '1',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2FO1CN01PcixuO1shzU1xav3G_%21%212212419065799-0-cib.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563756&t=5f986daa50035fec160db47bf04b65a2',
  //       title: '4罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 59.0,
  //       amount: 1,
  //       price: 89.0,
  //       size: {
  //         width: 50,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '2',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2FO1CN01PcixuO1shzU1xav3G_%21%212212419065799-0-cib.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563756&t=5f986daa50035fec160db47bf04b65a2',
  //       title: '4罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 69.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 50,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '3',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fl.guangdiu.com%2Ffd9fa14f61edfa03e87ff0f66c0bd173.jpg%3FimageView2%2F2%2Fw%2F400%2Fh%2F400%2Fformat%2Fjpg&refer=http%3A%2F%2Fl.guangdiu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=29777ed202a9303cdef859164ffb3386',
  //       title: '6罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 79.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 75,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '4',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fl.guangdiu.com%2Ffd9fa14f61edfa03e87ff0f66c0bd173.jpg%3FimageView2%2F2%2Fw%2F400%2Fh%2F400%2Fformat%2Fjpg&refer=http%3A%2F%2Fl.guangdiu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=29777ed202a9303cdef859164ffb3386',
  //       title: '6罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 89.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 75,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '5',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2FO1CN01PcixuO1shzU1xav3G_%21%212212419065799-0-cib.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=5cd53a5aac5a3c9d6cdcf7ea593cebd1',
  //       title: '4罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 69.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 50,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '6',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2FO1CN01PcixuO1shzU1xav3G_%21%212212419065799-0-cib.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=5cd53a5aac5a3c9d6cdcf7ea593cebd1',
  //       title: '4罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 69.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 50,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '7',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fl.guangdiu.com%2Ffd9fa14f61edfa03e87ff0f66c0bd173.jpg%3FimageView2%2F2%2Fw%2F400%2Fh%2F400%2Fformat%2Fjpg&refer=http%3A%2F%2Fl.guangdiu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=29777ed202a9303cdef859164ffb3386',
  //       title: '6罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 89.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 75,
  //         height: 50
  //       }
  //     },
  //     {
  //       id: '8',
  //       imgurl:
  //         'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fl.guangdiu.com%2Ffd9fa14f61edfa03e87ff0f66c0bd173.jpg%3FimageView2%2F2%2Fw%2F400%2Fh%2F400%2Fformat%2Fjpg&refer=http%3A%2F%2Fl.guangdiu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651563780&t=29777ed202a9303cdef859164ffb3386',
  //       title: '6罐装||英国爱他美白金版2段 6-12月 800G',
  //       uprice: 89.0,
  //       amount: 1,
  //       price: 79.0,
  //       size: {
  //         width: 75,
  //         height: 50
  //       }
  //     }
  //   ]
  //   resolve({ data: cartLists })
  // })
}

export function save_cartstuff(id, number) {
  return request({
    url: `/webapp/cart/save/${id}/${number}`,
    method: 'post'
  })
}

export function remove_cartstuff(serialId) {
  return request({
    url: `/webapp/cart/remove/${serialId}`,
    method: 'post'
  })
}

export function setNumber_cartstuff(data, number) {
  return request({
    url: `/webapp/cart/setNumber/${number}`,
    method: 'post',
    data
  })
}

export function get_cartstuff_number() {
  return request({
    url: '/webapp/cart/listNumber',
    method: 'get'
  })
}
