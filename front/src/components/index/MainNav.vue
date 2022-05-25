<template>
  <div class="container">
    <div class="nav-container">
      <ul>
        <li>
          <router-link :to="{ name: 'house' }">
            <div class="menu-item">
              <img
                class="menu-item-title deal-title"
                src="@/assets/dealInfo.png"
                width="360px"
              />
              <img
                class="menu-item-img"
                src="@/assets/menu.jpg"
                width="400px"
              />
            </div>
          </router-link>
        </li>
        <li>
          <router-link to="#">
            <div class="menu-item">
              <img
                class="menu-item-title news-title"
                src="@/assets/news_title.png"
                width="500px"
              />
              <img
                class="menu-item-img"
                src="@/assets/news.jpg"
                width="700px"
              />
            </div>
          </router-link>
        </li>
      </ul>
      <ul>
        <li>
          <router-link :to="{ name: 'qna' }">
            <div class="menu-item notice-img">
              <img
                class="menu-item-title notice-title"
                src="@/assets/notice_title.png"
                width="400px"
              />
              <img
                class="menu-item-img"
                src="@/assets/notice_img.jpg"
                width="650px"
              />
            </div>
          </router-link>
        </li>
        <li>
          <router-link :to="{ name: 'board' }">
            <div class="menu-item">
              <img
                class="menu-item-title board-title"
                src="@/assets/board_title.png"
                width="380px"
              />
              <img
                class="menu-item-img"
                src="@/assets/board_img.jpg"
                width="430px"
              />
            </div>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainNav",
};
</script>

<style scoped>
.container {
  margin-top: 150px;
}
li {
  list-style-type: none;
  display: inline-block;
  margin-left: 30px;
  margin-right: 30px;
  text-align: center;
}

.nav-container {
  position: absolute;
  /* margin-top: 1000px; */
}

.menu-item-img {
  filter: brightness(80%);
}

.menu-item-title {
  position: absolute;
  z-index: 10;
}

.menu-item:hover {
  transform: scale(1.1, 1.1);
  transition: all 0.3s linear;
}

.menu-item-img:hover {
  filter: brightness(40%);
  transition: all 0.3s linear;
}

.deal-title {
  margin-top: 50px;
  margin-left: 20px;
}

.news-title {
  margin-left: 30px;
  margin-top: 300px;
}

.notice-title {
  margin-left: 200px;
  margin-top: 300px;
}

.board-title {
  margin-top: 30px;
}
</style>
