<template>
  <div>
    <h3 class="title">댓글 작성</h3>
    <form @submit="onSubmit">
      <div class="comment-container">
        <input
          type="text"
          id="comment"
          v-model="reply"
          class="comment-input"
          placeholder="댓글을 입력하세요."
        />
        <button type="submit" class="comment-submit-btn">작성</button>
      </div>
    </form>
    <table class="comment-tb">
      <thead>
        <tr>
          <th>작성자</th>
          <th>댓글</th>
          <th>작성일</th>
        </tr>
      </thead>
      <comment-list-item
        v-for="comment in comments"
        :key="comment.cno"
        v-bind="comment"
        v-on:refresh="getList"
      ></comment-list-item>
    </table>
  </div>
</template>

<script>
import CommentListItem from "@/components/board/comment/item/CommentListItem.vue";
import { listComment, writeComment } from "@/api/comment";

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentList",
  components: { CommentListItem },
  props: ["bno"],
  data() {
    return {
      reply: "",
      page: 1,
      startPage: 1,
      totalPage: 1,
      endPage: 1,
      comments: [],
    };
  },
  created() {
    this.getList();
    // console.log(this.comments);
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.reply && ((msg = "댓글을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.registComment();
    },
    registComment() {
      writeComment(
        0,
        {
          bno: this.bno,
          ccontent: this.reply,
          cwriter: this.userInfo.userid,
        },
        (response) => {
          console.log(response.data);
          this.getList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getList() {
      listComment(
        this.bno,
        this.page,
        (response) => {
          this.comments = response.data.commentList;
          this.page = response.data.page;
          this.startPage = response.data.startPage;
          this.totalPage = response.data.totalPage;
          this.endPage = response.data.endPage;
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.title {
  margin-top: 50px;
  margin-left: 50px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}

.comment-container {
  text-align: left;
  width: 100%;
  margin-bottom: 20px;
}

.comment-input {
  width: 70%;
  margin-left: 50px;
  margin-right: 10px;
  padding: 10px;
  border-radius: 3px;
  border: 1px solid gray;
}

.comment-submit-btn {
  width: 80px;
  padding: 10px;
  border-radius: 3px;
  border: none;
  background-color: gray;
  color: whitesmoke;
}

.comment-tb {
  width: 80%;
  margin-left: 50px;
  border-top: 2px solid rgb(228, 228, 228);
  border-bottom: 2px solid rgb(228, 228, 228);
}

.comment-tb th {
  padding: 10px;
}

.comment-tb tbody tr {
  border-top: 1px solid rgba(220, 220, 220, 0.498);
}
</style>
