using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;

namespace WebShop.SL.ServiceLayerImpl
{
    public class ProductService : IProductService
    {
        private IUnitOfWork _uow;

        public ProductService(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public void Create(ProductDTO product)
        {
            _uow.ProductOperations.Create(product);
            _uow.SaveChanges();
        }
    }
}
