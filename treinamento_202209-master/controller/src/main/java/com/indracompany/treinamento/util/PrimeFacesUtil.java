package com.indracompany.treinamento.util;

import java.util.Map;

import javax.faces.component.UIComponent;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;

import com.indracompany.treinamento.util.FacesUtil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Prime Faces Util
 *
 * @author George Lima, galcantarap[at]indracompany[dot]com
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrimeFacesUtil {

  public static void executeJavaScript(final String script) {
    PrimeFaces.current().executeScript(script);
  }

  public static void hideDialog(final Dialog dialog) {
    PrimeFacesUtil.hideDialog(dialog.resolveWidgetVar());
  }

  public static void hideDialog(final String dialogWidgetVar) {
    PrimeFacesUtil.executeJavaScript("PF('" + dialogWidgetVar + "').hide();");
  }

  public static void scrollTo(final String name) {
    PrimeFaces.current().scrollTo(name);
  }

  public static void openDynamicDialog(Map<String, Object> configuracoes, String pagina) {
    PrimeFaces.current().dialog().openDynamic(pagina, configuracoes, null);
  }

  public static void showDialog(final Dialog dialog) {
    PrimeFacesUtil.showDialog(dialog.resolveWidgetVar());
  }

  public static void showDialog(final String dialogWidgetVar) {
    PrimeFacesUtil.executeJavaScript("PF('" + dialogWidgetVar + "').show();");
  }

  public static void update(final String name) {
    final PrimeFaces requestContext = PrimeFaces.current();
    if (requestContext.isAjaxRequest()) {
      requestContext.ajax().update(name);
    }
  }

  public static void resetValues(final String id) {
    PrimeFaces.current().resetInputs(id);
  }

  public static void reiniciarVisualizacaoDataTable(String id) {
    UIComponent dataTable = FacesUtil.obterComponente(id);
    if (dataTable instanceof DataTable) {
      ((DataTable) dataTable).setFirst(0);
    }
  }

}
