$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
  const button = $(event.relatedTarget);
  const codigoTitulo = button.data('codigo');

  const modal = $(this);
  const form = modal.find('form');
  let action = form.attr('data-url-base');
  const tituloDescricao = button.data('descricao')

  if (!action.endsWith('/')) {
    action += '/';
  }

  form.attr('action', action + codigoTitulo)

  modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + tituloDescricao + '</strong>?');
});

$(function() {
  $('[rel="tooltip"]').tooltip();
});