// Compiled by ClojureScript 1.9.908 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__17539){
var map__17540 = p__17539;
var map__17540__$1 = ((((!((map__17540 == null)))?((((map__17540.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__17540.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__17540):map__17540);
var m = map__17540__$1;
var n = cljs.core.get.call(null,map__17540__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__17540__$1,new cljs.core.Keyword(null,"name","name",1843675177));
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1((function (){var temp__4657__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4657__auto__)){
var ns = temp__4657__auto__;
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(ns),"/"].join('');
} else {
return null;
}
})()),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__17542_17564 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__17543_17565 = null;
var count__17544_17566 = (0);
var i__17545_17567 = (0);
while(true){
if((i__17545_17567 < count__17544_17566)){
var f_17568 = cljs.core._nth.call(null,chunk__17543_17565,i__17545_17567);
cljs.core.println.call(null,"  ",f_17568);

var G__17569 = seq__17542_17564;
var G__17570 = chunk__17543_17565;
var G__17571 = count__17544_17566;
var G__17572 = (i__17545_17567 + (1));
seq__17542_17564 = G__17569;
chunk__17543_17565 = G__17570;
count__17544_17566 = G__17571;
i__17545_17567 = G__17572;
continue;
} else {
var temp__4657__auto___17573 = cljs.core.seq.call(null,seq__17542_17564);
if(temp__4657__auto___17573){
var seq__17542_17574__$1 = temp__4657__auto___17573;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__17542_17574__$1)){
var c__16350__auto___17575 = cljs.core.chunk_first.call(null,seq__17542_17574__$1);
var G__17576 = cljs.core.chunk_rest.call(null,seq__17542_17574__$1);
var G__17577 = c__16350__auto___17575;
var G__17578 = cljs.core.count.call(null,c__16350__auto___17575);
var G__17579 = (0);
seq__17542_17564 = G__17576;
chunk__17543_17565 = G__17577;
count__17544_17566 = G__17578;
i__17545_17567 = G__17579;
continue;
} else {
var f_17580 = cljs.core.first.call(null,seq__17542_17574__$1);
cljs.core.println.call(null,"  ",f_17580);

var G__17581 = cljs.core.next.call(null,seq__17542_17574__$1);
var G__17582 = null;
var G__17583 = (0);
var G__17584 = (0);
seq__17542_17564 = G__17581;
chunk__17543_17565 = G__17582;
count__17544_17566 = G__17583;
i__17545_17567 = G__17584;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_17585 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__15511__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__15511__auto__)){
return or__15511__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_17585);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_17585)))?cljs.core.second.call(null,arglists_17585):arglists_17585));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,["\n  Please see http://clojure.org/",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,["\n  Please see http://clojure.org/special_forms#",cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__17546_17586 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__17547_17587 = null;
var count__17548_17588 = (0);
var i__17549_17589 = (0);
while(true){
if((i__17549_17589 < count__17548_17588)){
var vec__17550_17590 = cljs.core._nth.call(null,chunk__17547_17587,i__17549_17589);
var name_17591 = cljs.core.nth.call(null,vec__17550_17590,(0),null);
var map__17553_17592 = cljs.core.nth.call(null,vec__17550_17590,(1),null);
var map__17553_17593__$1 = ((((!((map__17553_17592 == null)))?((((map__17553_17592.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__17553_17592.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__17553_17592):map__17553_17592);
var doc_17594 = cljs.core.get.call(null,map__17553_17593__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_17595 = cljs.core.get.call(null,map__17553_17593__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_17591);

cljs.core.println.call(null," ",arglists_17595);

if(cljs.core.truth_(doc_17594)){
cljs.core.println.call(null," ",doc_17594);
} else {
}

var G__17596 = seq__17546_17586;
var G__17597 = chunk__17547_17587;
var G__17598 = count__17548_17588;
var G__17599 = (i__17549_17589 + (1));
seq__17546_17586 = G__17596;
chunk__17547_17587 = G__17597;
count__17548_17588 = G__17598;
i__17549_17589 = G__17599;
continue;
} else {
var temp__4657__auto___17600 = cljs.core.seq.call(null,seq__17546_17586);
if(temp__4657__auto___17600){
var seq__17546_17601__$1 = temp__4657__auto___17600;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__17546_17601__$1)){
var c__16350__auto___17602 = cljs.core.chunk_first.call(null,seq__17546_17601__$1);
var G__17603 = cljs.core.chunk_rest.call(null,seq__17546_17601__$1);
var G__17604 = c__16350__auto___17602;
var G__17605 = cljs.core.count.call(null,c__16350__auto___17602);
var G__17606 = (0);
seq__17546_17586 = G__17603;
chunk__17547_17587 = G__17604;
count__17548_17588 = G__17605;
i__17549_17589 = G__17606;
continue;
} else {
var vec__17555_17607 = cljs.core.first.call(null,seq__17546_17601__$1);
var name_17608 = cljs.core.nth.call(null,vec__17555_17607,(0),null);
var map__17558_17609 = cljs.core.nth.call(null,vec__17555_17607,(1),null);
var map__17558_17610__$1 = ((((!((map__17558_17609 == null)))?((((map__17558_17609.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__17558_17609.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__17558_17609):map__17558_17609);
var doc_17611 = cljs.core.get.call(null,map__17558_17610__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_17612 = cljs.core.get.call(null,map__17558_17610__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_17608);

cljs.core.println.call(null," ",arglists_17612);

if(cljs.core.truth_(doc_17611)){
cljs.core.println.call(null," ",doc_17611);
} else {
}

var G__17613 = cljs.core.next.call(null,seq__17546_17601__$1);
var G__17614 = null;
var G__17615 = (0);
var G__17616 = (0);
seq__17546_17586 = G__17613;
chunk__17547_17587 = G__17614;
count__17548_17588 = G__17615;
i__17549_17589 = G__17616;
continue;
}
} else {
}
}
break;
}
} else {
}

if(cljs.core.truth_(n)){
var temp__4657__auto__ = cljs.spec.alpha.get_spec.call(null,cljs.core.symbol.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.ns_name.call(null,n))].join(''),cljs.core.name.call(null,nm)));
if(cljs.core.truth_(temp__4657__auto__)){
var fnspec = temp__4657__auto__;
cljs.core.print.call(null,"Spec");

var seq__17560 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__17561 = null;
var count__17562 = (0);
var i__17563 = (0);
while(true){
if((i__17563 < count__17562)){
var role = cljs.core._nth.call(null,chunk__17561,i__17563);
var temp__4657__auto___17617__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___17617__$1)){
var spec_17618 = temp__4657__auto___17617__$1;
cljs.core.print.call(null,["\n ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),":"].join(''),cljs.spec.alpha.describe.call(null,spec_17618));
} else {
}

var G__17619 = seq__17560;
var G__17620 = chunk__17561;
var G__17621 = count__17562;
var G__17622 = (i__17563 + (1));
seq__17560 = G__17619;
chunk__17561 = G__17620;
count__17562 = G__17621;
i__17563 = G__17622;
continue;
} else {
var temp__4657__auto____$1 = cljs.core.seq.call(null,seq__17560);
if(temp__4657__auto____$1){
var seq__17560__$1 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__17560__$1)){
var c__16350__auto__ = cljs.core.chunk_first.call(null,seq__17560__$1);
var G__17623 = cljs.core.chunk_rest.call(null,seq__17560__$1);
var G__17624 = c__16350__auto__;
var G__17625 = cljs.core.count.call(null,c__16350__auto__);
var G__17626 = (0);
seq__17560 = G__17623;
chunk__17561 = G__17624;
count__17562 = G__17625;
i__17563 = G__17626;
continue;
} else {
var role = cljs.core.first.call(null,seq__17560__$1);
var temp__4657__auto___17627__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___17627__$2)){
var spec_17628 = temp__4657__auto___17627__$2;
cljs.core.print.call(null,["\n ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),":"].join(''),cljs.spec.alpha.describe.call(null,spec_17628));
} else {
}

var G__17629 = cljs.core.next.call(null,seq__17560__$1);
var G__17630 = null;
var G__17631 = (0);
var G__17632 = (0);
seq__17560 = G__17629;
chunk__17561 = G__17630;
count__17562 = G__17631;
i__17563 = G__17632;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map
