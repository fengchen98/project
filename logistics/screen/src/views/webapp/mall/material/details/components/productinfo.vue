<template>
  <div class="info">
    <div class="aside">
      <div class="list">其他尺寸</div>
      <div class="content">
        <ul>
          <li>
            <img
              src="https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg"
              alt=""
            >
            <h5>可封口小气泡袋 BM1</h5>
            <div class="size">100mm x 165mm + 30mm</div>
          </li>
          <li>
            <img
              src="https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg"
              alt=""
            >
            <h5>可封口小气泡袋 BM1</h5>
            <div class="size">100mm x 165mm + 30mm</div>
          </li>
          <li>
            <img
              src="https://img.send2china.co.uk/site_media%2Fmedia%2Fmall%2Fimg%2FBM1-2.jpg"
              alt=""
            >
            <h5>可封口小气泡袋 BM1</h5>
            <div class="size">100mm x 165mm + 30mm</div>
          </li>
        </ul>
      </div>
    </div>
    <div class="detail">
      <div class="list">图文详情</div>
      <div class="text">
        可自封小气泡袋 （厚实型）<br>
        <p />
        优点， 由于封口上自带胶条，
        所以如果货品需要用气泡包装并且合并到箱子的时候气泡袋会给产品全方位的保护，
        并且可以提高包货速度， 不需要用气泡膜去剪裁， 也不需要用胶带纸去封，
        如果一个气泡袋不够 那可以先包好产品之后再放入另一个气泡袋再包一层<br>
        <p />
        气泡袋尺寸表<br>
        BM 1: 100mm x 165mm + 30mm封口<br>
        BM 2: 130mm x 205mm + 30mm封口<br>
        BM 3: 180mm x 280mm + 30mm封口<br>
        BM 4: 230mm x 280mm + 30mm封口<br>
        BM 5: 280mm x 355mm + 30mm封口<br>
        BM 6: 300mm x 430mm + 30mm封口<br>
        BM 7: 380mm x 430mm + 30mm封口<br>
        <p />
        如何计算应该用哪个尺寸的袋子， 请按照如下的计算<br>
        气泡袋所需宽度 = 货品宽 + 货品高 + 10mm<br>
        气泡袋所需长度 = 货品长 + 货品高 + 10mm<br>
        <p />
        举例<br>
        一个手机的长130mm, 宽度70mm， 厚度9mm,
        那建议使用的气泡信封尺寸就应该是：<br>
        气泡袋所需宽度 = 70mm + 9mm + 10mm = 89mm<br>
        气泡袋所需长度 = 130mm + 9mm + 10mm = 149mm<br>
        根据计算， 气泡膜需要的长宽就是 89mm x 149mm, 那根据现有的气泡袋的大小，
        最合适的就是BM1, 100x165+30mm， 封口的尺寸并不影响计算，
        所以不需要计算在内<br>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    id: {
      type: String,
      default: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.info {
  width: 100%;
  display: flex;
  justify-content: space-between;
  .list {
    width: 100%;
    height: 7vh;
    background-color: #f1f1f1;
    border: 1px solid #ccc;
    line-height: 7vh;
    font-size: 18px;
    text-align: center;
  }
  .aside {
    width: 18vw;
    min-height: 10vh;
    .content {
      ul {
        padding: 0;
        margin: 0;
      }
      li {
        list-style: none;
        border: 1px solid #ccc;
        border-top: none;
        text-align: center;
        img {
          width: 80%;
          height: 30vh;
        }
        h5 {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          font-size: 17px;
          margin: 15px 0;
        }
        .size {
          font-size: 15px;
          margin-bottom: 5px;
        }
      }
    }
  }
  .detail {
    width: 44vw;
    min-height: 20vh;
    .text {
      padding: 10px;
      padding-left: 20px;
      line-height: 30px;
      font-size: 17px;
    }
  }
}
</style>
