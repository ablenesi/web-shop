using System.Collections.Generic;

using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerContracts
{
    public interface IProductOperations
    {
        void Create(ProductDTO product);
        IEnumerable<ProductDTO> Get();
    }
}
