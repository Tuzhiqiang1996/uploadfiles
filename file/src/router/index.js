import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import(/* webpackChunkName: "home" */ "@/view/index.vue")
    },
    {
      path: "/HelloWorld",
      name: "HelloWorld",
      component: () =>
        import(/* webpackChunkName: "HelloWorld" */ "@/components/HelloWorld")
    },
    {
      path: "/Xjlist",
      name: "Xjlist",
      component: () =>
        import(/* webpackChunkName: "xlvue" */ "@/components/Xjlist")
    },
    {
      path: "/xls",
      name: "xls",
      component: () =>
        import(/* webpackChunkName: "xlvue" */ "@/components/xls")
    }
  ]
});
