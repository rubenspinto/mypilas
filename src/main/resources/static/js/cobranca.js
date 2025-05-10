$("#confirmacaoExclusaoModal").on("show.bs.modal", function (event) {
  const button = $(event.relatedTarget);
  const codigoTitulo = button.data("codigo");

  const modal = $(this);
  const form = modal.find("form");
  let action = form.attr("data-url-base");
  const tituloDescricao = button.data("descricao");

  if (!action.endsWith("/")) {
    action += "/";
  }

  form.attr("action", action + codigoTitulo);

  modal
    .find(".modal-body span")
    .html(
      "Tem certeza que deseja excluir o título <strong>" +
        tituloDescricao +
        "</strong>?"
    );
});

$(function () {
  $('[rel="tooltip"]').tooltip();
  $(".js-currency").maskMoney({
    decimal: ",",
    thousands: ".",
    allowZero: true,
  });

  $(".js-atualizar-status").on("click", function (event) {
    event.preventDefault();

    const botaoReceber = $(event.currentTarget);
    const urlReceber = botaoReceber.attr("href");

    const response = $.ajax({
      url: urlReceber,
      method: "PUT"
    });

    response.done(function(e){
      var codigoTitulo = botaoReceber.data('codigo');
      $('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + e +'</span>');
      botaoReceber.hide();
    });

    response.fail(function(e){
      console.log(e);
      alert("Erro recebendo cobrança")
    });
  });
});
