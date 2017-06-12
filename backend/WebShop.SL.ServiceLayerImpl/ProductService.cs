using System;
using System.Collections.Generic;
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

        public void Create(ProductDTO productDTO)
        {
            _uow.ProductOperations.Create(productDTO);
        }

        public IEnumerable<ProductDTO> GetAll()
        {
            return _uow.ProductOperations.Get();
        }
    }
}
