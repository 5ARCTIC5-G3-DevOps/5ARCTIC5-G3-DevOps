package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.entities.Product;

import java.util.List;

public interface IInvoiceDetailService {
    List<InvoiceDetail> retriveAllInvoicesDetail();
    InvoiceDetail getInvoiceDetail(long idInvoiceDetail);
    InvoiceDetail addInvoiceDetail(InvoiceDetail invoiceDetail);
    void deleteInvoiceDetail(long idInvoiceDetail);
    //Invoice addInvoiceDetailToInvoice(Long invoiceId, InvoiceDetail invoiceDetail);

}
