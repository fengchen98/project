<template>
  <div class="container">
    <div class="banner">
      <img src="/static/images/banner.jpg" alt="banner">
    </div>
    <div class="content">
      <h1>SAD退税文件申请</h1>
      <div class="boxWrapper">
        <div class="img-box">
          <img src="/static/images/sad.png" alt="">
        </div>
        <hr>
        <div class="box">
          <p class="title">SAD文件是什么？</p>
          <p>
            SAD文件全称 Single Administrative Document
            ，是物品离境直接向英国海关申报获得的返回文件。这份文件可以证明您要申请退税的包裹已经成功离开英国。英国政府对SAD文件的详细介绍可以看这里:
            <a
              href="https://www.gov.uk/government/publications/import-and-export-single-administrative-document-full-8-part-set-c88-1-8"
            >点击此处</a>
          </p>
          <img src="/static/images/sad_doc.png" alt="">
        </div>
        <hr>
        <div class="box">
          <p class="title">谁可以申请SAD文件？</p>
          <p>只要您有英国注册公司，就可以在系统申请使用。</p>
          <p>
            在申请页面内登记您的英国公司注册信息，VAT税号等，我们的工作人员将在1个工作日内审核通过。申请链接:
            <a href="/">点击此处</a>
          </p>
        </div>
        <hr>
        <div class="box">
          <p class="title">SAD有什么用</p>
          <p>平台杂货自营路线，都可以申请SAD文件退税！</p>
          <p>
            有了SAD文件后，可以用于向HMRC证明你需要退税的这批货确实已经从英国出口。您只需要将这个文件交给自己公司的会计，就可以确保这笔退税没有任何后顾之忧，可以凭此文件完全合规的拿到VAT退税。
          </p>
        </div>
        <hr>
        <div class="box">
          <p class="title">SAD跟CN23有什么区别？</p>
          <p>一张图让您看明白区别:</p>
          <img src="/static/images/sad_doc2.png" alt="">
          <p>关于SAD收费的详细说明:</p>
          <p>
            目前已对ETK，香港专线，杂货小包，化妆品小包，套装专线开通SAD申请服务。
          </p>
          <p>
            以上服务分为2个类别收费，每个类别不论包裹数量每周申请时都会在同一个SAD文件内，只收费40英镑:
          </p>
          <li>类别1: E特快，香港专线</li>
          <li>类别2: 杂货小包，护肤品小包，套装专线</li>
        </div>
        <hr>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="scss" scoped>
.container {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100%;
}
.banner {
  //   transform: translateY(-20px);
  position: absolute;
  z-index: -1;
}
.content {
  position: relative;
  width: 50%;
  margin: 0 auto;
  padding: 10vh 0;
  h1 {
    font-size: 3rem;
    font-weight: 400;
    color: #fff;
    margin: 0;
    margin-bottom: 5rem;
  }
  .boxWrapper {
    background-color: rgba(243, 249, 250, 0.95);
    padding: 2rem;
    hr {
      margin: 2rem 0;
      border: none;
      border-bottom: 1px solid #bdc3c7;
    }
    .img-box {
      padding: 2rem;
      img {
        width: 100%;
      }
    }
    .box {
      padding: 1rem;
      font-size: 1.5rem;
      line-height: 2rem;
      font-weight: 600;
      .title {
        font-size: 2.5rem;
        font-weight: 600;
        color: #000;
      }
      img {
        margin: 4rem 10%;
        width: 80%;
      }
      p,
      li {
        line-height: 3rem;
        span {
          font-weight: 700;
          color: #e67e22;
        }
        h4 {
          display: inline;
        }
      }
    }
  }
}
</style>
