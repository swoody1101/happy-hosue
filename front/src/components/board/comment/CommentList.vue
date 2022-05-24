<template>
  <div>
    <form @submit="onSubmit">
      <label for="comment">댓글</label>
      <input type="text" id="comment" v-model="reply" />
      <button type="submit">입력</button>
    </form>
    <h2>댓글</h2>
    <table border="1">
      <thead>
        <tr>
          <td>작성자</td>
          <td>댓글</td>
          <td>작성일</td>
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
          cwriter: "admin",
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

<style></style>
